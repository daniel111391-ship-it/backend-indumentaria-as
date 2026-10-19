package com.indumentaria.stock.service;

import com.indumentaria.stock.RecursoNoEncontradoException;
import com.indumentaria.stock.dto.PrendaDTO;
import com.indumentaria.stock.entity.Prenda;
import com.indumentaria.stock.mapper.PrendaMapper;
import com.indumentaria.stock.repository.PrendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrendaService {

    @Autowired
    private PrendaRepository prendaRepository;

    @Autowired
    private PrendaMapper prendaMapper;

    public String registrarPrenda(PrendaDTO dto) {
        Prenda prenda = prendaMapper.dtoToEntity(dto);
        prendaRepository.save(prenda);
        return "¡Prenda registrada con éxito en el sistema!";
    }

    public List<PrendaDTO> verInventario() {
        return prendaRepository.findAll().stream()
                .map(prendaMapper::entityToDTO)
                .toList();
    }

    public PrendaDTO buscarPorId(Long id) {
        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la prenda con ID: " + id));
        return prendaMapper.entityToDTO(prenda);
    }

    public String actualizarPrenda(Long id, PrendaDTO dto) {
        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la prenda con ID: " + id));

        prenda.setNombre(dto.nombre());
        prenda.setCategoria(dto.categoria());
        prenda.setTalle(dto.talle());
        prenda.setPrecio(dto.precio());
        prenda.setStock(dto.stock());

        prendaRepository.save(prenda);
        return "¡Prenda con ID " + id + " actualizada con éxito!";
    }

    public String eliminarPrenda(Long id) {
        if (prendaRepository.existsById(id)) {
            prendaRepository.deleteById(id);
            return "¡Prenda con ID " + id + " eliminada con éxito!";
        } else {
            return "Error: No se encontró la prenda con ID " + id;
        }
    }
}