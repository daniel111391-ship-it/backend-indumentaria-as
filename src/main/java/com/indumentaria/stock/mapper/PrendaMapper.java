package com.indumentaria.stock.mapper;

import com.indumentaria.stock.dto.PrendaRequestDTO;
import com.indumentaria.stock.dto.PrendaResponseDTO;
import com.indumentaria.stock.entity.Prenda;
import com.indumentaria.stock.entity.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class PrendaMapper {

    public PrendaResponseDTO entityToDTO(Prenda prenda) {
        Long proveedorId = (prenda.getProveedor() != null) ? prenda.getProveedor().getId() : null;
        return new PrendaResponseDTO(
                prenda.getId(),
                prenda.getNombre(),
                prenda.getCategoria(),
                prenda.getTalle(),
                prenda.getPrecio(),
                prenda.getStock(),
                proveedorId
        );
    }

    public Prenda dtoToEntity(PrendaRequestDTO dto, Proveedor proveedor) {
        Prenda prenda = new Prenda();
        prenda.setNombre(dto.nombre());
        prenda.setCategoria(dto.categoria());
        prenda.setTalle(dto.talle());
        prenda.setPrecio(dto.precio());
        prenda.setStock(dto.stock());
        prenda.setProveedor(proveedor);
        return prenda;
    }
}