package com.indumentaria.stock.dto;

import jakarta.validation.constraints.NotBlank;

public record ProveedorRequestDTO(
        @NotBlank(message = "El nombre de contacto es obligatorio")
        String nombreContacto,

        @NotBlank(message = "El nombre de la empresa es obligatorio")
        String nombreEmpresa,

        @NotBlank(message = "El teléfono es obligatorio")
        String telefono
) {}