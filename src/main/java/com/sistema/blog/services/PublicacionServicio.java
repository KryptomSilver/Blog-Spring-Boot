package com.sistema.blog.services;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;

public interface PublicacionServicio {
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

	public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina,String ordenarPor,String sortDir);

	public PublicacionDTO obtenerPublicacionPorId(long id);

	public PublicacionDTO actualizarPublicacion(long id, PublicacionDTO publicacionDTO);

	public void eliminarPublicacion(long id);
}
