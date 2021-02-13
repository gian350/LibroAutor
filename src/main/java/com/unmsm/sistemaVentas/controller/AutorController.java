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
import org.springframework.web.util.UriComponentsBuilder;

import com.unmsm.sistemaVentas.model.Autor;
import com.unmsm.sistemaVentas.service.AutorService;

@Controller
@RequestMapping(value="/autor")
public class AutorController {

	@Autowired
	private AutorService _autorService;
	
	// retornar lista de autores
	// esto es otra manera de 
	//GET
	@RequestMapping(value="/autores", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Autor>> getAutores(@RequestParam(value="name", required=false) String nombre) {
		
		List<Autor> autores = new ArrayList<>();
		//ejecutamos una validacion
		
		if(nombre == null) {
			autores = _autorService.findAllAutor();
			if (autores.isEmpty()) {
				//return new ResponseEntity(HttpStatus.NO_CONTENT);
				return ResponseEntity.noContent().build();// el metodo build construye el responseEntity pero sin cuerpo, solo con estado
			}
			
		//	return new ResponseEntity<List<Autor>>(autores, HttpStatus.OK);
			return ResponseEntity.ok(autores);//en el caso del status OK , nos brinda la opcion de darle el cuerpo
		} else {
			Autor autor = _autorService.findByName(nombre);
			if(autor == null) {
				//return new ResponseEntity(HttpStatus.NOT_FOUND);
				return ResponseEntity.notFound().build();
			}
			
			autores.add(autor);
			return ResponseEntity.ok(autores);
		}

	}
	
	// creacion de un autor
	//POST
	@RequestMapping(value="/createAutor", method = RequestMethod.POST, headers = "Accept=application/json")
	//Response Entity es la forma mas simple para manejar informacion en json
	//RequestBody es el cuerpo de una solicitud que se enviará desde el cliente a la webservice
	public ResponseEntity<Autor> createAutor(@RequestBody Autor autor){
		
		// validamos el objeto que traigo tenga datos
		
		//si esta vacio nos devolvera esto
		if (autor.getNombre().equals(null) || autor.getApellido().isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		// aqui guardamos al autor
		_autorService.saveAutor(autor);
		
		return new ResponseEntity<Autor>(autor, HttpStatus.CREATED);
	}
	
	// busqueda de un autor por id
	//GET
	@RequestMapping(value="/findAutor/{id}",method = RequestMethod.GET,headers = "Accept=application/json")
	//@PathVariable sirve para declarar un parametro como variable que se utilizara en la ruta del @RequestMapping, en este caso sera el id
	public ResponseEntity<Autor> findAutorById(@PathVariable int id){
		
		Autor autorEncontrado = _autorService.findById(id);
		return new ResponseEntity<Autor>(autorEncontrado,HttpStatus.OK); 
	}
	
	// eliminar autor por id
	//GET
	@RequestMapping(value="/deleteAutor/{id}",method = RequestMethod.DELETE,headers = "Accept=application/json")
	//@PathVariable sirve para declarar un parametro como variable que se utilizara en la ruta del @RequestMapping, en este caso sera el id
	public ResponseEntity<Autor> deleteAutorById(@PathVariable int id){
		Autor autor = _autorService.findById(id);
		if(autor == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		_autorService.deleteAutor(id);
		return new ResponseEntity<Autor>(autor,HttpStatus.OK); 
	}
	
	/*
	Diferencia entre metodo put y patch
	El metodo PATCH para estrablecer pequeñas partes del recurso mientras que PUT es para actualizar todo el recurso 
	*/
	
	// Actualizar autor por un autor
	//PUT
	@RequestMapping(value="/updateAutor/{id}",method = RequestMethod.PUT,headers = "Accept=application/json")
	public ResponseEntity<Autor> updateAutorById(@PathVariable int id,@RequestBody Autor autor){
		
		Autor currentAutor = _autorService.findById(id);
		
		if(currentAutor == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}else {
			
			// si se quiere actulizar la informacion por la misma informacion
			if(autor == currentAutor) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			
			currentAutor.setApellido(autor.getApellido());
			currentAutor.setNombre(autor.getNombre());
			
			_autorService.updateAutor(currentAutor);
			return new ResponseEntity<Autor>(currentAutor,HttpStatus.OK); 
		}
		
	}
	
	
	
	
	
	
	
	
	
}
