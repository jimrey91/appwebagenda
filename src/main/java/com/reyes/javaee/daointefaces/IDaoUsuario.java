package com.reyes.javaee.daointefaces;

import javax.persistence.EntityManager;

import com.reyes.javaee.entidades.Usuario;

public interface IDaoUsuario {
	
	public boolean insert(EntityManager em, Usuario us) throws Exception;
	public Usuario getByIdUsuario(EntityManager em, int idUsuario) throws Exception;
	public Usuario getByCorreoElectronico(EntityManager em, String correoElectronico) throws Exception;
	public boolean update(EntityManager em, Usuario us) throws Exception;

}
