package com.indumentaria.stock.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record PrendaRequestDTO(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "La categoría es obligatoria")
        String categoria,

        @NotBlank(message = "El talle es obligatorio")
        String talle,

        @Positive(message = "El precio debe ser mayor a cero")
        double precio,

        @PositiveOrZero(message = "El stock no puede ser negativo")
        int stock,

        @NotNull(message = "El ID del proveedor es obligatorio")
        Long proveedorId
) {}