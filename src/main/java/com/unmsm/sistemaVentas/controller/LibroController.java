package com.unmsm.sistemaVentas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unmsm.sistemaVentas.model.Autor;
import com.unmsm.sistemaVentas.model.Libro;
import com.unmsm.sistemaVentas.service.AutorService;
import com.unmsm.sistemaVentas.service.LibroService;

@Controller
@RequestMapping(value="/libro")
public class LibroController {
	
	@Autowired
	private LibroService _libroService;
	
	@Autowired
	private AutorService _autorService;
	
	//GET
	@RequestMapping(value="/libros", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Libro>> getLibros(@RequestParam(value="name", required=false) String nombre) {
			
		List<Libro> libros = new ArrayList<>();
		//ejecutamos una validacion
			
		if(nombre == null) {
			libros = _libroService.findAllLibro();
			if (libros.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
				
			return new ResponseEntity<List<Libro>>(libros, HttpStatus.OK);
		} else {
			Libro libro = _libroService.findByName(nombre);
			if(libro == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
				
			libros.add(libro);
			return new ResponseEntity<List<Libro>>(libros, HttpStatus.OK);
		}

	}
	
	
	// creacion de un libro
	//POST
	@RequestMapping(value="/createLibro/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	//Response Entity es la forma mas simple para manejar informacion en json
	//RequestBody es el cuerpo de una solicitud que se enviará desde el cliente a la webservice
	public ResponseEntity<?> createLibro(@PathVariable int id,@RequestBody Libro libro){

		// primero corroboramos que exista el Autor
		Autor autor = _autorService.findById(id);

		if(autor == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}else {// existe el autor
			
			if(libro.getNombre().isEmpty() || libro.getCategoria().isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			libro.setAutor(autor);
			// aqui guardamos el libro
			_libroService.saveLibro(libro);
			return new ResponseEntity<Libro>(libro, HttpStatus.CREATED);
			
		}
		
		
	}
	
	
	// busqueda de un autor por id
	//GET
	@RequestMapping(value="/findLibro/{id}",method = RequestMethod.GET,headers = "Accept=application/json")
	//@PathVariable sirve para declarar un parametro como variable que se utilizara en la ruta del @RequestMapping, en este caso sera el id
	public ResponseEntity<Libro> findAutorById(@PathVariable int id){
		
		Libro libroEncontrado = _libroService.findById(id);
		return new ResponseEntity<Libro>(libroEncontrado,HttpStatus.OK); 
	}
	
	
	// eliminacion de un libro 
	//DELETE
	@RequestMapping(value="/deleteLibro/{id}",method = RequestMethod.DELETE,headers = "Accept=application/json")
	//@PathVariable sirve para declarar un parametro como variable que se utilizara en la ruta del @RequestMapping, en este caso sera el id
	public ResponseEntity<Libro> deleteLibroById(@PathVariable int id){
		
		Libro libro = _libroService.findById(id);
		
		if(libro == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		_libroService.deleteLibro(id);
		return new ResponseEntity<Libro>(libro,HttpStatus.OK); 
	}
	
	
	// Actualizar autor por un autor
	//PUT
	@RequestMapping(value="/updateLibro/{idLibro}",method = RequestMethod.PUT,headers = "Accept=application/json")
	public ResponseEntity<Libro> updateLibroById(@PathVariable int idLibro,@RequestBody Libro libro){
			
		Libro currentLibro = _libroService.findById(idLibro);
		
		if(currentLibro == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
				
			// si se quiere actulizar la informacion por la misma informacion
			if(libro == currentLibro) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			
			currentLibro.setCategoria(libro.getCategoria());
			currentLibro.setNombre(libro.getNombre());
			
			_libroService.updateLibro(currentLibro);
			return new ResponseEntity<Libro>(currentLibro,HttpStatus.OK); 
		}
		
	}
	
	

	
	
}
