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

import com.reyes.javaee.dao.DaoActividad;
import com.reyes.javaee.daointefaces.IDaoActividad;
import com.reyes.javaee.ejbinterfaces.IEjbActividad;
import com.reyes.javaee.entidades.Actividad;
import com.reyes.javaee.entidades.Usuario;

@Stateless
public class EjbActividad implements IEjbActividad {
	
	private EntityManagerFactory emf = null;
	private EntityManager em;
	private EntityTransaction et = null;
	
	private Actividad actividad;
	private List<Actividad> listaActividad;
	
	public EjbActividad(){
		actividad = new Actividad();
		actividad.setUsuario(new Usuario());
	}
	
	@Override
	public Map<String, String> insert() {
		
		Map<String, String> returnMap = new HashMap<>();
		String mensajeGeneral="";
		
		
		try {
			
			actividad.setEstado(false);
									
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String fechaActual = formato.format(new Date());
			
			actividad.setFechaRegistro(fechaActual);
			actividad.setFechaModificacion(fechaActual);
			
			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();
			
			Set<ConstraintViolation<Actividad>> constraint = validator.validate(actividad);
			
			for (ConstraintViolation<Actividad> item : constraint) {
				mensajeGeneral+=item.getMessage()+"<br>";
			}
			
			
			IDaoActividad iDaoActividad = new DaoActividad();
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
						
			if(!mensajeGeneral.equals("")){
				
				et.rollback();
				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				return returnMap;
			}
			
			iDaoActividad.insert(em, actividad);
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
	public void getByIdUsuario(int idUsuario) {	
		
		try {
					
			
			IDaoActividad iDaoActividad = new DaoActividad();
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
						
			listaActividad = iDaoActividad.getByIdUsuario(em, idUsuario);
			
			et.commit();
			
		} catch (Exception e) {

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
	public void getByIdUsuarioAndEstado(int idUsuario, boolean estado) {
		
		try {
					
			
			IDaoActividad iDaoActividad = new DaoActividad();
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
						
			listaActividad = iDaoActividad.getByIdUsuarioAndEstado(em, idUsuario, estado);
			
			et.commit();
			
		} catch (Exception e) {

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
	public void cambiarEstado(int idActividad, boolean estado){
	
		try {			
			
			IDaoActividad iDaoActividad = new DaoActividad();
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			et.begin();
						
			actividad = iDaoActividad.getByIdActividad(em, idActividad);
			
			actividad.setEstado(estado);
			
			iDaoActividad.cambiarEstado(em, actividad);
			
			et.commit();
			
		} catch (Exception e) {

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
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;

	}

	@Override
	public Actividad getActividad() {

		return actividad;
	}

	@Override
	public void setListaActividad(List<Actividad> listaActividad) {
		this.listaActividad = listaActividad;

	}

	@Override
	public List<Actividad> getListaActividad() {
		// TODO Auto-generated method stub
		return listaActividad;
	}



}
