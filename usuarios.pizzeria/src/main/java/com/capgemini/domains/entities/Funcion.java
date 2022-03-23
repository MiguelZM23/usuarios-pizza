package com.capgemini.domains.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.capgemini.domains.core.entities.EntityBase;


/**
 * The persistent class for the funciones database table.
 * 
 */
@Entity
@Table(name="funciones")
@NamedQuery(name="Funcion.findAll", query="SELECT f FROM Funcion f")
public class Funcion extends EntityBase<Funcion> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_funcion")
	private int idFuncion;

	

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	@NotBlank
	@Length(min=2, max=10)
	private String rol;
	
	public Funcion() {
	}

	public int getIdFuncion() {
		return this.idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idFuncion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Funcion)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcion other = (Funcion) obj;
		return idFuncion == other.idFuncion;
	}

	@Override
	public String toString() {
		return "Funcion [idFuncion=" + idFuncion + ", usuario=" + usuario + ", rol=" + rol + "]";
	}
	
	
	
	

}