package com.jhonatan.authservice.repository;

import com.jhonatan.authservice.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public class UsuarioRepository extends JpaRepository<Usuario, Long> {
}
