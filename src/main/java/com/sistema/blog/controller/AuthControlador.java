package com.sistema.blog.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.dto.LoginDTO;
import com.sistema.blog.dto.RegistroDTO;
import com.sistema.blog.models.Rol;
import com.sistema.blog.models.Usuario;
import com.sistema.blog.repositories.RolRepositorio;
import com.sistema.blog.repositories.UsuarioRepositorio;
import com.sistema.blog.seguridad.JWTAuthResponseDTO;
import com.sistema.blog.seguridad.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private RolRepositorio rolRepositorio;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/iniciarSesion")
	public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Obtenemos el token
		String token = jwtTokenProvider.generarToken(authentication);
		return ResponseEntity.ok(new JWTAuthResponseDTO(token));
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> resgistroUsario(@RequestBody RegistroDTO registroDTO) {
		if (usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usario ya existe", HttpStatus.BAD_REQUEST);
		}
		if (usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Ese email de usario ya existe", HttpStatus.BAD_REQUEST);
		}
		Usuario usuario = new Usuario();
		usuario.setNombre(registroDTO.getNombre());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		usuarioRepositorio.save(usuario);
		return new ResponseEntity<>("Usuario registrado existosamente", HttpStatus.OK);
	}
}
