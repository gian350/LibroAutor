package com.unmsm.sistemaVentas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unmsm.sistemaVentas.dao.LibroDao;
import com.unmsm.sistemaVentas.model.Libro;

@Service("libroService")
@Transactional
public class LibroServiceImpl implements LibroService {

	/*
	 Marcamos beans con @Service para indicar que mantiene la lógica empresarial.
	 Por lo tanto, no hay otra especialidad excepto usarla en la capa de servicio
	 */
	
	@Autowired
	private LibroDao _libroDao;
	
	@Override
	public void saveLibro(Libro libro) {
		// TODO Auto-generated method stub
		_libroDao.saveLibro(libro);
	}

	@Override
	public void deleteLibro(int idLibro) {
		// TODO Auto-generated method stub
		_libroDao.deleteLibro(idLibro);
	}

	@Override
	public void updateLibro(Libro libro) {
		// TODO Auto-generated method stub
		_libroDao.updateLibro(libro);
	}

	@Override
	public List<Libro> findAllLibro() {
		// TODO Auto-generated method stub
		return _libroDao.findAllLibro();
	}

	@Override
	public Libro findById(int idLibro) {
		// TODO Auto-generated method stub
		return _libroDao.findById(idLibro);
	}

	@Override
	public Libro findByName(String nombre) {
		// TODO Auto-generated method stub
		return _libroDao.findByName(nombre);
	}

}
