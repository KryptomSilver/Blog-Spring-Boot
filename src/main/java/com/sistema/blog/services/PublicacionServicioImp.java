package com.sistema.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.exceptions.ResourceNotFoundException;
import com.sistema.blog.models.Publicacion;
import com.sistema.blog.repositories.PublicacionRepositorio;

@Service
public class PublicacionServicioImp implements PublicacionServicio {
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = mapearEntidad(publicacionDTO);
		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);
		PublicacionDTO publicacionRespuesta = mapearDTO(nuevaPublicacion);
		return publicacionRespuesta;
	}

	@Override
	public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
		List<Publicacion> publicaciones = publicacionRepositorio.findAll();
		return publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", id));
		return mapearDTO(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(long id, PublicacionDTO publicacionDTO) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", id));
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
		return mapearDTO(publicacionActualizada);
	}

	@Override
	public void eliminarPublicacion(long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", id));
		publicacionRepositorio.delete(publicacion);
		
	}
	
	// Convierte entidad a DTO
	private PublicacionDTO mapearDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();

		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		publicacionDTO.setContenido(publicacion.getContenido());
		return publicacionDTO;
	}

	// Convierte DTO a entidad
	private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = new Publicacion();

		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		return publicacion;
	}

}
