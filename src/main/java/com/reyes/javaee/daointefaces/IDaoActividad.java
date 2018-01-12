package com.reyes.javaee.daointefaces;

import java.util.List;

import javax.persistence.EntityManager;

import com.reyes.javaee.entidades.Actividad;

public interface IDaoActividad {
	
	public boolean insert(EntityManager em, Actividad actividad)throws Exception;
	public List<Actividad> getByIdUsuario(EntityManager em, int idUsuario) throws Exception;
	public List<Actividad> getByIdUsuarioAndEstado(EntityManager em, int idUsuario, boolean estado) throws Exception;
	public Actividad getByIdActividad(EntityManager em, int idActividad)throws Exception;
	public boolean cambiarEstado(EntityManager em, Actividad actividad) throws Exception;
}
