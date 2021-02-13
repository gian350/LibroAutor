package com.unmsm.sistemaVentas.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.unmsm.sistemaVentas.model.Autor;
import com.unmsm.sistemaVentas.model.Libro;

@Repository
@Transactional
public class LibroDaoImpl extends AbstractSession implements LibroDao{

	@Override
	public void saveLibro(Libro libro) {
		// TODO Auto-generated method stub
		getSession().persist(libro);
	}

	@Override
	public void deleteLibro(int idLibro) {
		// TODO Auto-generated method stub
		Libro libro = findById(idLibro);
		if(libro != null) {
			getSession().delete(libro);
		}
	}

	@Override
	public void updateLibro(Libro libro) {
		// TODO Auto-generated method stub
		getSession().update(libro);
	}

	@Override
	public List<Libro> findAllLibro() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Libro").list();
	}

	@Override
	public Libro findById(int idLibro) {
		// TODO Auto-generated method stub
		return (Libro) getSession().get(Libro.class,idLibro);
	}

	@Override
	public Libro findByName(String nombre) {
		// TODO Auto-generated method stub
		return (Libro) getSession().createQuery("from Libro where nombre = :nombre").setParameter("nombre", nombre).uniqueResult();
	}

	
}
