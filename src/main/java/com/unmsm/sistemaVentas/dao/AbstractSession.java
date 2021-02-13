package com.unmsm.sistemaVentas.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSession {
	
	//cuando ejecutemos la herencia se ejecuten los metoods
	
	@Autowired
	private SessionFactory sessionFactory;
	// llama a la unica implementacion de la interfaz SessionFactory y lo inyecta a sessionFactory
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
