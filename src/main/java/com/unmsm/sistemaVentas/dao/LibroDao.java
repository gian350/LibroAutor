package com.unmsm.sistemaVentas.dao;

import java.util.List;

import com.unmsm.sistemaVentas.model.Libro;

public interface LibroDao {

	void saveLibro(Libro libro);
	
	void deleteLibro(int idLibro);
	
	void updateLibro(Libro libro);
	
	List<Libro> findAllLibro();
	
	Libro findById(int idLibro);
	
	Libro findByName(String nombre);
	
}
