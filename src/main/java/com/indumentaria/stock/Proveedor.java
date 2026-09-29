package com.indumentaria.stock;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreContacto;
    private String nombreEmpresa;
    private String telefono;

    // Relación: Un proveedor abastece muchas prendas
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Prenda> prendas;
}