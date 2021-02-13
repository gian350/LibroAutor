package com.unmsm.sistemaVentas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unmsm.sistemaVentas.dao.AutorDao;
import com.unmsm.sistemaVentas.model.Autor;

@Service("autorService")
@Transactional
public class AutorServiceImpl implements AutorService {

	//https://dzone.com/articles/how-does-spring-transactional (explicacion de @transaccional)
	
	@Autowired
	private AutorDao _autorDao;
	
	@Override
	public void saveAutor(Autor autor) {
		// TODO Auto-generated method stub
		_autorDao.saveAutor(autor);
	}

	@Override
	public void deleteAutor(int idAutor) {
		// TODO Auto-generated method stub
		_autorDao.deleteAutor(idAutor);
	}

	@Override
	public void updateAutor(Autor autor) {
		// TODO Auto-generated method stub
		_autorDao.updateAutor(autor);
	}

	@Override
	public List<Autor> findAllAutor() {
		// TODO Auto-generated method stub
		return _autorDao.findAllAutor();
	}

	@Override
	public Autor findById(int idAutor) {
		// TODO Auto-generated method stub
		return _autorDao.findById(idAutor);
	}

	@Override
	public Autor findByName(String nombre) {
		// TODO Auto-generated method stub
		return _autorDao.findByName(nombre);
	}

}
