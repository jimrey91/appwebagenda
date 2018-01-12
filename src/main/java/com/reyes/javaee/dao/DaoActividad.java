package com.reyes.javaee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.reyes.javaee.daointefaces.IDaoActividad;
import com.reyes.javaee.entidades.Actividad;

public class DaoActividad implements IDaoActividad {

	@Override
	public boolean insert(EntityManager em, Actividad actividad) throws Exception {
		em.persist(actividad);
		return true;
	}

	@Override
	public List<Actividad> getByIdUsuario(EntityManager em, int idUsuario) throws Exception {
		
		TypedQuery<Actividad> query = em.createNamedQuery("Actividad.getByIdUsuario", Actividad.class);
		query.setParameter("idUsuario", idUsuario);
		
		return query.getResultList();

	}

	@Override
	public List<Actividad> getByIdUsuarioAndEstado(EntityManager em, int idUsuario, boolean estado) throws Exception {
		
		TypedQuery<Actividad> query = em.createNamedQuery("Actividad.getByIdUsuarioAndEstado", Actividad.class);
		
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("estado", estado);
		
		return query.getResultList();
	}

	@Override
	public Actividad getByIdActividad(EntityManager em, int idActividad) throws Exception {
		
		return em.find(Actividad.class, idActividad);
	}

	@Override
	public boolean cambiarEstado(EntityManager em, Actividad actividad) throws Exception {
		
		em.merge(actividad);
		
		return true;
	}

}
