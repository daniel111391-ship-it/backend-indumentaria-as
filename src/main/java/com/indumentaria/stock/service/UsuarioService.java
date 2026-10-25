package com.indumentaria.stock.service;

import com.indumentaria.stock.RecursoNoEncontradoException;
import com.indumentaria.stock.dto.UsuarioRequestDTO;
import com.indumentaria.stock.dto.UsuarioResponseDTO;
import com.indumentaria.stock.entity.Usuario;
import com.indumentaria.stock.mapper.UsuarioMapper;
import com.indumentaria.stock.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::entityToDTO)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el usuario con ID: " + id));
        return usuarioMapper.entityToDTO(usuario);
    }

    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("El correo ya está en uso");
        }

        Usuario usuario = usuarioMapper.dtoToEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.password()));
        usuario.setRol("ROLE_USER");

        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.entityToDTO(guardado);
    }

    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el usuario con ID: " + id));

        if (!usuario.getEmail().equals(dto.email()) && usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("El correo ya está en uso");
        }

        usuario.setNombre(dto.nombre());
        usuario.setEmail(dto.email());
        usuario.setPassword(passwordEncoder.encode(dto.password()));

        Usuario actualizado = usuarioRepository.save(usuario);
        return usuarioMapper.entityToDTO(actualizado);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe el usuario con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}