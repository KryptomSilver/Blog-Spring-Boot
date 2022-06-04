package com.sistema.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.models.Publicacion;

public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {

}
