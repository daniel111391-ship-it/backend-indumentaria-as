package com.indumentaria.stock.mapper;

import com.indumentaria.stock.dto.UsuarioDTO;
import com.indumentaria.stock.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    // Traduce de Entidad (Base de datos) a DTO (Respuesta para Postman/Web)
    public UsuarioDTO entityToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol()
        );
    }

    // Traduce de DTO (Datos que entran desde Postman/Web) a Entidad (Para guardar)
    public Usuario dtoToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        // El ID no se lo pasamos porque MySQL lo genera automáticamente (Auto Increment)
        usuario.setNombre(dto.nombre());
        usuario.setEmail(dto.email());
        usuario.setRol(dto.rol());

        return usuario;
    }
}