package com.reyes.javaee.ejbinterfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.reyes.javaee.entidades.Actividad;

@Local
public interface IEjbActividad {
	
	public Map<String, String> insert();
	public void getByIdUsuario(int idUsuario);
	public void getByIdUsuarioAndEstado(int idUsuario, boolean estado);
	public void cambiarEstado(int idActividad, boolean estado);
	
	public void setActividad(Actividad actividad);
	public Actividad getActividad();
	public void setListaActividad(List<Actividad> listaActividad);
	public List<Actividad> getListaActividad();
	

	
}
