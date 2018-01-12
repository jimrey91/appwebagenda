package com.reyes.javaee.appwebagenda;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.reyes.javaee.entidades.Usuario;

public class Prueba {

	public static void main(String[] args) {
		insertar();
	}
	
	public static void insertar(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("appwebagenda");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			Usuario usuario = new Usuario();
			usuario.setNombre("jaime");
			usuario.setApellido("daniel");
			usuario.setFechaNacimiento("1991/09/16");
			usuario.setCorreoElectronico("jimmy@outlook.com");
			usuario.setContrasenia("abcd");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String fechaActual = formato.format(new Date());
			usuario.setFechaRegistro(fechaActual);
			usuario.setFechaActualizacion(fechaActual);
			
			et.begin();
			em.persist(usuario);
			et.commit();
			System.out.println("Registrado");
		} catch (Exception e) {
			et.rollback();
			System.out.println(e.getMessage());
		}finally{
			em.close();
		}
	}
}
