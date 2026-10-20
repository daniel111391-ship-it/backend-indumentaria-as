package com.indumentaria.stock.repository;

import com.indumentaria.stock.entity.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrendaRepository extends JpaRepository<Prenda, Long> {
    List<Prenda> findByProveedorId(Long proveedorId);
}