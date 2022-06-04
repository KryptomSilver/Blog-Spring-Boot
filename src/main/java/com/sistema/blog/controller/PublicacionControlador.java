package com.sistema.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.services.PublicacionServicio;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionControlador {
	@Autowired
	private PublicacionServicio publicacionServicio;

	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionServicio.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<PublicacionDTO> listaPublicaciones() {
		return publicacionServicio.obtenerTodasLasPublicaciones();
	}
	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name="id") long id){
		return ResponseEntity.ok(publicacionServicio.obtenerPublicacionPorId(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicacion(@PathVariable(name="id") long id, @RequestBody PublicacionDTO publicacionDTO){
		PublicacionDTO publicacionActualizada = publicacionServicio.actualizarPublicacion(id, publicacionDTO);
		return new ResponseEntity<>(publicacionActualizada,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name="id") long id) {
		publicacionServicio.eliminarPublicacion(id);
		return new ResponseEntity<String>("Publicación eliminada con exito",HttpStatus.OK);
	}
}
