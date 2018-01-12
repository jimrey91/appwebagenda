package com.reyes.javaee.ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.reyes.javaee.appwebagenda.Helper;
import com.reyes.javaee.dao.DaoUsuario;
import com.reyes.javaee.daointefaces.IDaoUsuario;
import com.reyes.javaee.ejbinterfaces.IEjbUsuario;
import com.reyes.javaee.entidades.Usuario;

@Stateless
public class EjbUsuario implements IEjbUsuario {
	
	private EntityManagerFactory emf = null;
	private EntityManager em;
	private EntityTransaction et = null;
	
	private Usuario usuario;
	private List<Usuario> listaUsuario;
	
	private String correoElectronicoAnterior;
	private String contraseniaRepita;
	private String contraseniaAnterior;
	private String contraseniaNueva;
	
	public EjbUsuario(){
		usuario = new Usuario();
	}
	
	public EntityManager getEntityManager(){
		return em;
	}

	@Override
	public Map<String, String> insert() {
	
		Map<String, String> returnMap = new HashMap<>();
		String mensajeGeneral="";
		
		try {
			
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String fechaActual = formato.format(new Date());
			
			usuario.setFechaRegistro(fechaActual);
			usuario.setFechaActualizacion(fechaActual);
			
			if(!usuario.getContrasenia().equals(contraseniaRepita)){
				mensajeGeneral+="Las contraseñas no coiciden<br>";
			}
			
			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();
			
			Set<ConstraintViolation<Usuario>> constraint = validator.validate(usuario);
			
			for (ConstraintViolation<Usuario> item : constraint) {
				mensajeGeneral+=item.getMessage()+"<br>";
			}
			
			usuario.setContrasenia(new Helper().encrypt(usuario.getContrasenia()));
			
			IDaoUsuario iDaoUsuario = new DaoUsuario();
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
			
			if(iDaoUsuario.getByCorreoElectronico(em, usuario.getCorreoElectronico())!=null){
				mensajeGeneral+="El correo electronico ya fue registrado<br>";
			}
			
			if(!mensajeGeneral.equals("")){
				
				et.rollback();
				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				return returnMap;
			}
			
			iDaoUsuario.insert(em, usuario);
			et.commit();
			returnMap.put("correcto", "Si");
			returnMap.put("mensajeGeneral", "Registro realizado correctamente<br>");
			return returnMap;
			
		} catch (Exception e) {
			if(et!=null){
				et.rollback();
			}
			System.out.println("Error:" + e.getMessage());
			
			returnMap.put("correcto", "No");
			return returnMap;
			
		}finally{
			if(em!=null){
				em.close();
				em=null;
			}
			if(emf!=null){
				emf.close();
				emf=null;
			}
			et=null;
		}
		
	}
		
	@Override
	public void getByIdUsuario(int idUsuario){
		try {
			IDaoUsuario iDaoUsuario = new DaoUsuario();
			emf = Persistence.createEntityManagerFactory("appwebagenda");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
			
			usuario = iDaoUsuario.getByIdUsuario(em, idUsuario);
			
			et.commit();
			
						
		} catch (Exception e) {
			if(et!=null){
				et.rollback();
			}
			System.out.println("Error:" + e.getMessage());
			}finally{
			if(em!=null){
				em.close();
				em=null;
			}
			if(emf!=null){
				emf.close();
				emf=null;
			}
			et=null;
		}
	}
	
	
	@Override
	public Map<String, String> update() {
		
		Map<String, String> returnMap = new HashMap<>();
		String mensajeGeneral="";
		
		try {
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String fechaActual = formato.format(new Date());
			
			usuario.setFechaActualizacion(fechaActual);
			
			if(!contraseniaAnterior.equals("")){
				if(usuario.getContrasenia().equals(new Helper().encrypt(contraseniaAnterior))){
					usuario.setContrasenia(new Helper().encrypt(contraseniaNueva));
				}else{
					mensajeGeneral +="La contraseña anterior no es la correcta<br>";
				}
			}
			
			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();
			
			Set<ConstraintViolation<Usuario>> constraint = validator.validate(usuario);
			
			for (ConstraintViolation<Usuario> item : constraint) {
				mensajeGeneral+=item.getMessage()+"<br>";
			}
			
			if(mensajeGeneral!=""){
				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				
				return returnMap;
			}
			
			IDaoUsuario iDaoUsuario = new DaoUsuario();
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
			
			if(iDaoUsuario.getByCorreoElectronico(em, usuario.getCorreoElectronico())!=null){
				if(!iDaoUsuario.getByCorreoElectronico(em, usuario.getCorreoElectronico()).getCorreoElectronico().equals(correoElectronicoAnterior)){
					mensajeGeneral += "El correo electronico ya fue registrado con anterioridad<br>";
				}
			}
			
			if(!mensajeGeneral.equals("")){
				et.rollback();
				
				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				
				return returnMap;
			}
			
			iDaoUsuario.update(em, usuario);
			
			et.commit();
			
			returnMap.put("correcto", "Si");
			returnMap.put("mensajeGeneral", "Datos guardados correctamente");
			
			return returnMap;
			
						
		} catch (Exception e) {
			if(et!=null){
				et.rollback();
			}
			
			System.out.println("Error:" + e.getMessage());
			
			returnMap.put("correcto", "No");
			
			return returnMap;
			
			}finally{
				
			if(em!=null){
				em.close();
				em=null;
			}
			if(emf!=null){
				emf.close();
				emf=null;
			}
			et=null;
		}
		
	}
	
	@Override
	public void getByCorreoElectronico(String correoElectronico){
		try {
			IDaoUsuario iDaoUsuario = new DaoUsuario();
			emf = Persistence.createEntityManagerFactory("appwebagenda");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
			
			usuario = iDaoUsuario.getByCorreoElectronico(em, correoElectronico);
			
			et.commit();
			
						
		} catch (Exception e) {
			if(et!=null){
				et.rollback();
			}
			System.out.println("Error:" + e.getMessage());
			}finally{
			if(em!=null){
				em.close();
				em=null;
			}
			if(emf!=null){
				emf.close();
				emf=null;
			}
			et=null;
		}
	}
	
	@Override
	public Map<String, String> login(String contrasenia) {

		
		Map<String, String> returnMap = new HashMap<>();
		String mensajeGeneral="";
		try {

			if(usuario==null){
				mensajeGeneral+="Usuario o contraseña incorrecta<br>";
			}else{
				if(new Helper().encrypt(contrasenia).equals(usuario.getContrasenia())){
					returnMap.put("correcto", "Si");
					returnMap.put("mensajeGeneral", "Inicio de sesion correcto");
					return returnMap;
				}else{
					mensajeGeneral+="Usuario o contraseña incorrecta<br>";
				}
			}
			
			
			if(mensajeGeneral!=""){

				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				return returnMap;
			}
			return null;
			
		} catch (Exception e) {
			if(et!=null){
				et.rollback();
			}
			System.out.println("Error:" + e.getMessage());
			
			returnMap.put("correcto", "No");
			return returnMap;
			
		}finally{
			if(em!=null){
				em.close();
				em=null;
			}
			if(emf!=null){
				emf.close();
				emf=null;
			}
			et=null;
		}
		
	}
	
	@Override
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

	}

	@Override
	public Usuario getUsuario() {
		return usuario;
	}
	
	@Override
	public void setListarUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;

	}

	@Override
	public List<Usuario> getListarUsuario() {
		return listaUsuario;
	}
	
	@Override
	public void setCorreoElectronicoAnterior(String correoElectronicoAnterior) {
		this.correoElectronicoAnterior = correoElectronicoAnterior;
	}

	@Override
	public String getCorreoElectronicoAnterior() {
		return correoElectronicoAnterior;
	}
	
	@Override
	public void setContraseniaRepita(String contraseniaRepita) {
		this.contraseniaRepita = contraseniaRepita;
	}
	
	@Override
	public String getContraseniaRepita() {
		return contraseniaRepita;
	}

	@Override
	public void setContraseniaAnterior(String contraseniaAnterior) {
		this.contraseniaAnterior = contraseniaAnterior;
	}

	@Override
	public String getContraseniaAnterior() {
		return contraseniaAnterior;
	}

	@Override
	public void setContraseniaNueva(String contraseniaNueva) {
		this.contraseniaNueva = contraseniaNueva;
		
	}

	@Override
	public String getContraseniaNueva() {
		return contraseniaNueva;
	}


}
