package com.indumentaria.stock.service;

import com.indumentaria.stock.dto.UsuarioDTO;
import com.indumentaria.stock.entity.Usuario;
import com.indumentaria.stock.mapper.UsuarioMapper;
import com.indumentaria.stock.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    // Lógica para registrar a un empleado o administrador
    public String crearUsuario(UsuarioDTO dto) {
        // 1. Usamos el mapper para convertir el DTO que llega a una Entidad
        Usuario nuevoUsuario = usuarioMapper.dtoToEntity(dto);

        // 2. Guardamos la entidad en la base de datos
        usuarioRepository.save(nuevoUsuario);

        return "¡Usuario de Indumentaria AS registrado con éxito!";
    }

    // Lógica para listar a todos los usuarios
    public List<UsuarioDTO> obtenerTodos() {
        // 1. Buscamos todas las entidades en la base de datos
        List<Usuario> usuarios = usuarioRepository.findAll();

        // 2. Usamos el mapper para convertirlas todas a DTOs y enviarlas
        return usuarios.stream()
                .map(usuarioMapper::entityToDTO)
                .collect(Collectors.toList());
    }
}