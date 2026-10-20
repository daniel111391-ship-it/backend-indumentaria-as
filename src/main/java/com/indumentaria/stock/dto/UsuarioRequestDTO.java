package com.indumentaria.stock.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser un email válido")
        String email,

        @NotBlank(message = "El rol es obligatorio")
        String rol
) {}