package com.reyes.javaee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.reyes.javaee.daointefaces.IDaoUsuario;
import com.reyes.javaee.entidades.Usuario;

public class DaoUsuario implements IDaoUsuario {

	@Override
	public boolean insert(EntityManager em, Usuario usuario) throws Exception {
		em.persist(usuario);
		return true;
	}

	@Override
	public Usuario getByIdUsuario(EntityManager em, int idUsuario) throws Exception {
		// TODO Auto-generated method stub
		return em.find(Usuario.class, idUsuario);
	}

	@Override
	public boolean update(EntityManager em, Usuario usuario) throws Exception {
		em.merge(usuario);
		return true;
	}

	@Override
	public Usuario getByCorreoElectronico(EntityManager em, String correoElectronico) throws Exception {
		
		
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.getByCorreoElectronico", Usuario.class);
		query.setParameter("correoElectronico", correoElectronico);
		List<Usuario> listaUsuario = query.setMaxResults(1).getResultList();
		
		if(listaUsuario.size()>0){
			return listaUsuario.get(0);
		}
		
		return null;
	}

}
