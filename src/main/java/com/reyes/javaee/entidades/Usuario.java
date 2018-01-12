package com.reyes.javaee.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.getByCorreoElectronico", query="SELECT u FROM Usuario u where u.correoElectronico=:correoElectronico")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	
	@NotNull(message="El campo \"Apellido\" es requerido")
	@Size(min=1, max=40, message="El campo \"Apellido\" debe tener la longitud entre 1 - 40")
	private String apellido;
	
	@NotNull(message="El campo \"Contraseña\" es requerido")
	@Size(min=4, max=700,  message="El campo \"Contraseña\" debe tener la longitud entre 1 - 40")
	private String contrasenia;
	
	@NotNull(message="El campo \"Correo Electronico\" es requerido")
	@Size(min=6, max=700,  message="El campo \"Correo Electronico\" debe tener la longitud entre 1 - 40")
	@Pattern(regexp="[a-zA-Z0-9\\.\\-\\_]+\\@[a-zA-Z0-9\\-\\_]+\\.[a-zA-Z]{2,4}", message="El campo \"Correo Electronico\" no cumple con formato requerido")
	private String correoElectronico;

	@NotNull(message="El campo \"Fecha de Actualizacion\" es requerido")
	@Pattern(regexp="(\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2})|(\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}\\.[0-9]{1,3})", message="El formato de la fecha de actualizacion no es el correcto")
	private String fechaActualizacion;

	@NotNull(message="El campo \"Fecha de Nacimiento\" es requerido")
	@Pattern(regexp="\\d{4}\\-\\d{2}\\-\\d{2}", message="El campo \"Fecha de Nacimiento\" no cumple el formato adecuado")
	private String fechaNacimiento;

	@NotNull(message="El campo \"Fecha de Registro\" es requerido")
	@Pattern(regexp="(\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2})|(\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}\\.[0-9]{1,3})", message="El formato de la fecha de registro no es el correcto")
	private String fechaRegistro;

	@NotNull(message="El campo \"Nombre\" es requerido")
	@Size(min=1, max=70,  message="El campo \"Nombre\" debe tener la longitud entre 1 - 40")
	private String nombre;

	//bi-directional many-to-one association to Actividad
	@OneToMany(mappedBy="usuario")
	private List<Actividad> actividads;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividad> getActividads() {
		return this.actividads;
	}

	public void setActividads(List<Actividad> actividads) {
		this.actividads = actividads;
	}

	public Actividad addActividad(Actividad actividad) {
		getActividads().add(actividad);
		actividad.setUsuario(this);

		return actividad;
	}

	public Actividad removeActividad(Actividad actividad) {
		getActividads().remove(actividad);
		actividad.setUsuario(null);

		return actividad;
	}

}