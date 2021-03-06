package com.sistema.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.exceptions.ResourceNotFoundException;
import com.sistema.blog.models.Publicacion;
import com.sistema.blog.repositories.PublicacionRepositorio;

@Service
public class PublicacionServicioImp implements PublicacionServicio {

	@Autowired
	private ModelMapper modelMapper;

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
	public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor,
			String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
				: Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
		Page<Publicacion> publicaciones = publicacionRepositorio.findAll(pageable);
		List<Publicacion> listaPublicaciones = publicaciones.getContent();
		List<PublicacionDTO> contenido = listaPublicaciones.stream().map(publicacion -> mapearDTO(publicacion))
				.collect(Collectors.toList());
		PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
		publicacionRespuesta.setContenido(contenido);
		publicacionRespuesta.setNumeroPagina(numeroDePagina);
		publicacionRespuesta.setMedidaPagina(medidaDePagina);
		publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
		publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
		publicacionRespuesta.setUltima(publicaciones.isLast());
		return publicacionRespuesta;
	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicaci??n", "id", id));
		return mapearDTO(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(long id, PublicacionDTO publicacionDTO) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicaci??n", "id", id));
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
		return mapearDTO(publicacionActualizada);
	}

	@Override
	public void eliminarPublicacion(long id) {
		Publicacion publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicaci??n", "id", id));
		publicacionRepositorio.delete(publicacion);

	}

	// Convierte entidad a DTO
	private PublicacionDTO mapearDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);
		return publicacionDTO;
	}

	// Convierte DTO a entidad
	private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);
		return publicacion;
	}

}
