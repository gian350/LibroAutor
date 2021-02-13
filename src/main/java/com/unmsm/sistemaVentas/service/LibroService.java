package com.unmsm.sistemaVentas.service;

import java.util.List;

import com.unmsm.sistemaVentas.model.Libro;

public interface LibroService {

	void saveLibro(Libro libro);
	
	void deleteLibro(int idLibro);
	
	void updateLibro(Libro libro);
	
	List<Libro> findAllLibro();
	
	Libro findById(int idLibro);
	
	Libro findByName(String nombre);
	
}
