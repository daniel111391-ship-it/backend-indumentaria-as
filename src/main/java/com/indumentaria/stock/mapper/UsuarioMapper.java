package com.indumentaria.stock.mapper;

import com.indumentaria.stock.dto.UsuarioRequestDTO;
import com.indumentaria.stock.dto.UsuarioResponseDTO;
import com.indumentaria.stock.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO entityToDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol()
        );
    }

    public Usuario dtoToEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.nombre());
        usuario.setEmail(dto.email());
        usuario.setRol(dto.rol());
        return usuario;
    }
}