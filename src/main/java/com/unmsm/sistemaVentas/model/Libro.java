package com.unmsm.sistemaVentas.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="libro")
public class Libro implements Serializable{
	
	@Id
	@Column(name="idLibro")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLibro;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="categoria")
	private String categoria;
	
	// clase dominante, osea aqui se coloca dentro del atributo autor la llave foranea con la cual se identifica
	// tiene que ser EAGER por que sino ocurre un error 
	// En este caso colocar cascade = CascadeType.ALL no es nesesario por que si elimino un libro , se eliminara el autor de ese libro 
	// pero si ese mismo autor escribio otros libros , esos otros libros se convertirian en libros sin autor osea huerfanos.
	// Ejemplo de como usar cascade(https://stackoverflow.com/questions/13027214/what-is-the-meaning-of-the-cascadetype-all-for-a-manytoone-jpa-association)
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idAutor")
	private Autor autor;
	
	public Libro() {
		super();
	}

	public Libro(int idLibro, String nombre, String categoria, Autor autor) {
		super();
		this.idLibro = idLibro;
		this.nombre = nombre;
		this.categoria = categoria;
		this.autor = autor;
	}



	public int getIdLibro() {
		
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
	
}
