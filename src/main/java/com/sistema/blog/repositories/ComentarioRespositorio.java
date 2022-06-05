package com.sistema.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.models.Comentario;

public interface ComentarioRespositorio extends JpaRepository<Comentario, Long> {
	public List<Comentario> findByPublicacionId(long publicacionId);
}
