package com.unmsm.sistemaVentas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="autor")
public class Autor implements Serializable{
	
	@Id
	@Column(name="idAutor")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAutor;
	
	// todos los nombres de las columnas en minuscula
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	//se mapea con el nombre del atributo con el cual esta relacionado esta clase y se llama autor y se encuentra
	//en la clase Libro ya que es la clase dominada y es bidireccional
	// @JsonIgnore es para que al momento de desserializar el json cuando el cliente envia la solicitud al objeto autor , no tome en 
	// cuenta este atributo y lo coloque en null
	// Colocar cascade = CascadeType.ALL seria de mucha utilidad ya que al eliminar un autor se eliminaria todos los libros que escribio 

	/*
	 Nota
	 1.Se puede hacer un get autor y que te devuelva los libros incluidos 
	 	Esto dependera del fetch, ya que si colocamos EAGER a autor y lazy a libro colocando un @jsonignore en Autor
	 2.Se puede hacer un get de libro y que te devuelva los autores incluidos
	 	Esto dependera del fetch, ya que si colocamos EAGER a Libro y lazy a Autor colocando un @jsonignore en Libro
	 	
	 En cualquier caso todo funcionaria igual , aunque yo me acomodo al segundo caso
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "autor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Libro> list_libros;
	
	public Autor() {
		super();
	}
	
	public Autor(int idAutor, String nombre, String apellido) {
		super();
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public List<Libro> getList_libros() {
		return list_libros;
	}
	
	public void setList_libros(List<Libro> list_libros) {
		this.list_libros = list_libros;
	}
	
	
	
	
	
	
	
	
}
