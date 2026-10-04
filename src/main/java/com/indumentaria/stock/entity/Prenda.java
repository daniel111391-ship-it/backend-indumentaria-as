package com.indumentaria.stock.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prendas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoria;
    private String talle;
    private double precio;
    private int stock;
    // Relación: Muchas prendas pertenecen a un solo proveedor
    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
}