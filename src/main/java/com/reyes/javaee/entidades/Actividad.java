package com.reyes.javaee.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the actividad database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Actividad.getByIdUsuario", query="select a from Actividad a where a.usuario.idUsuario=:idUsuario"),
	@NamedQuery(name="Actividad.getByIdUsuarioAndEstado", query="select a from Actividad a where a.usuario.idUsuario=:idUsuario and a.estado=:estado")
	/*@NamedQuery(name="Actividad.getByIdUsuario", query="select a from Actividad a where a.idUsuario=:idUsuario")*/
})
/*@NamedQuery(name="Actividad.findAll", query="SELECT a FROM Actividad a")*/
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idActividad;

	@Lob
	private String descripcion;

	private boolean estado;

	private String fechaHoraFin;

	private String fechaHoraInicio;

	private String fechaModificacion;

	private String fechaRegistro;

	private String lugar;

	private String nombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

	public Actividad() {
	}

	public int getIdActividad() {
		return this.idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getFechaHoraFin() {
		return this.fechaHoraFin;
	}

	public void setFechaHoraFin(String fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public String getFechaHoraInicio() {
		return this.fechaHoraInicio;
	}

	public void setFechaHoraInicio(String fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public String getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}