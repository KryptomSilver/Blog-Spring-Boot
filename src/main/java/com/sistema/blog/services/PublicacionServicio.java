package com.sistema.blog.services;

import java.util.List;

import com.sistema.blog.dto.PublicacionDTO;

public interface PublicacionServicio {
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	public List<PublicacionDTO> obtenerTodasLasPublicaciones();
	public PublicacionDTO obtenerPublicacionPorId(long id);
	public PublicacionDTO actualizarPublicacion(long id ,PublicacionDTO publicacionDTO);
	public void eliminarPublicacion(long id);
}
