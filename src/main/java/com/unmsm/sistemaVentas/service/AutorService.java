package com.unmsm.sistemaVentas.service;

import java.util.List;

import com.unmsm.sistemaVentas.model.Autor;

public interface AutorService {

	void saveAutor(Autor autor);
	
	void deleteAutor(int idAutor);
	
	void updateAutor(Autor autor);
	
	List<Autor> findAllAutor();
	
	Autor findById(int idAutor);
	
	Autor findByName(String nombre);
	
	
}
