package com.indumentaria.stock.mapper;

import com.indumentaria.stock.dto.PrendaDTO;
import com.indumentaria.stock.entity.Prenda;
import org.springframework.stereotype.Component;

@Component
public class PrendaMapper {

    public PrendaDTO entityToDTO(Prenda prenda) {
        return new PrendaDTO(
                prenda.getId(),
                prenda.getNombre(),
                prenda.getCategoria(),
                prenda.getTalle(),
                prenda.getPrecio(),
                prenda.getStock()
        );
    }

    public Prenda dtoToEntity(PrendaDTO dto) {
        Prenda prenda = new Prenda();
        prenda.setNombre(dto.nombre());
        prenda.setCategoria(dto.categoria());
        prenda.setTalle(dto.talle());
        prenda.setPrecio(dto.precio());
        prenda.setStock(dto.stock());
        return prenda;
    }
}