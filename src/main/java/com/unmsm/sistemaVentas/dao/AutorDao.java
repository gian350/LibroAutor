package com.unmsm.sistemaVentas.dao;

import java.util.List;

import com.unmsm.sistemaVentas.model.Autor;

public interface AutorDao {
	
	void saveAutor(Autor autor);
	
	void deleteAutor(int idAutor);
	
	void updateAutor(Autor autor);
	
	List<Autor> findAllAutor();
	
	Autor findById(int idAutor);
	
	Autor findByName(String nombre);
}

/*
 	void saveLibro(Libro libro);
	
	void deleteLibro(int libro);
	
	void updateLibro(Libro libro);
	
	List<Libro> findAll();
	
	void findById(int idLibro);
	
	void findByName(String nombre);
 */