package com.reyes.javaee.ejbinterfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.reyes.javaee.entidades.Usuario;

@Local
public interface IEjbUsuario {
	
	public Map<String, String> insert();
	public void getByIdUsuario(int idUsuario);
	public Map<String, String> update();
	public void getByCorreoElectronico(String correoElectronico);
	public Map<String, String> login(String contrasenia);
	public void setListarUsuario(List<Usuario> listaUsuario);
	public List<Usuario> getListarUsuario();
	public void setUsuario(Usuario usuario);
	public Usuario getUsuario();
	
	public void setCorreoElectronicoAnterior(String correoElectronicoAnterior);
	public String getCorreoElectronicoAnterior();
	public void setContraseniaAnterior(String contraseniaAnterior);
	public String getContraseniaAnterior();
	public void setContraseniaRepita(String contraseniaRepita);
	public String getContraseniaRepita();
	public void setContraseniaNueva(String contraseniaNueva);
	public String getContraseniaNueva();
}
