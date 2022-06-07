package com.sistema.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.models.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
	public Optional<Rol> findByNombre(String nombre);
}
