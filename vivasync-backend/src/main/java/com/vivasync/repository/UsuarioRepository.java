package com.vivasync.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivasync.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}