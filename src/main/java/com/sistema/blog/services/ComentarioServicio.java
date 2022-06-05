package com.sistema.blog.services;

import java.util.List;

import com.sistema.blog.dto.ComentarioDTO;

public interface ComentarioServicio {
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);

	public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId);

	public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId);

	public ComentarioDTO actualizarComentario(Long publicacionId, Long comenarioId,
			ComentarioDTO solicitudDeComentario);

	public void eliminarComentario(Long publicacionId, Long comentarioId);
}
