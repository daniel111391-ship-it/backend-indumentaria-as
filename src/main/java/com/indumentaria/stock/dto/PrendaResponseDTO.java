package com.indumentaria.stock.dto;

public record PrendaResponseDTO(
        Long id,
        String nombre,
        String categoria,
        String talle,
        double precio,
        int stock,
        Long proveedorId
) {}