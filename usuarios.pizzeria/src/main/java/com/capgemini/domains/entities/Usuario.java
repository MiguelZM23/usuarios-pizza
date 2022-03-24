package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.capgemini.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario extends EntityBase<Usuario> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;
	
	@NotBlank
	@Length(min=2, max=50)
	private String nombre;

	@Column(name="primer_apellido")
	@Length(min=0, max=50)
	private String primerApellido;

	@Column(name="segundo_apellido")
	@Length(min=0, max=50)
	private String segundoApellido;

	@NotBlank
	@Length(min=6, max=100)
	private String password;
	
	@Length(min=9, max=30)
	private String telefono;
	
	@NotBlank
	@Length(min=3, max=50)
	private String email;
	
	//bi-directional many-to-one association to Funcion
	@OneToMany(mappedBy="usuario")
	@JsonIgnore
	private List<Funcion> funciones;

	public Usuario() {
	}

	public Usuario(int usuarioId, String nombre2, String apellido1, String apellido2, String password2,
			String telefono2, String email2, List<Funcion> funciones) {
		super();
		this.idUsuario = usuarioId;
		this.nombre = nombre2;
		this.primerApellido = apellido1;
		this.segundoApellido = apellido2;
		this.password = password2;
		this.telefono = telefono2;
		this.email = email2;
		this.funciones = funciones;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Funcion> getFunciones() {
		return this.funciones;
	}

	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
	}

	public Funcion addFuncione(Funcion funcione) {
		getFunciones().add(funcione);
		funcione.setUsuario(this);

		return funcione;
	}

	public Funcion removeFuncione(Funcion funcione) {
		getFunciones().remove(funcione);
		funcione.setUsuario(null);

		return funcione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Usuario)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return idUsuario == other.idUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", primerApellido=" + primerApellido
				+ ", segundoApellido=" + segundoApellido + ", password=" + password + ", telefono=" + telefono
				+ ", email=" + email + ", funciones=" + funciones + "]";
	}
	
	
	

}