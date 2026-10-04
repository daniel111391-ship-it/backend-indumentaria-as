package com.indumentaria.stock.repository;

import com.indumentaria.stock.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}