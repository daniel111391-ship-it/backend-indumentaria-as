package com.indumentaria.stock;

public record PrendaDTO(
        Long id,
        String nombre,
        String categoria,
        String talle,
        double precio,
        int stock
) {
}