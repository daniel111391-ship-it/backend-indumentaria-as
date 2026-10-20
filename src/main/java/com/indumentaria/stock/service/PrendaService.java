package com.indumentaria.stock.service;

import com.indumentaria.stock.RecursoNoEncontradoException;
import com.indumentaria.stock.dto.PrendaRequestDTO;
import com.indumentaria.stock.dto.PrendaResponseDTO;
import com.indumentaria.stock.entity.Prenda;
import com.indumentaria.stock.entity.Proveedor;
import com.indumentaria.stock.mapper.PrendaMapper;
import com.indumentaria.stock.repository.PrendaRepository;
import com.indumentaria.stock.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrendaService {

    private final PrendaRepository prendaRepository;
    private final ProveedorRepository proveedorRepository;
    private final PrendaMapper prendaMapper;

    public PrendaService(PrendaRepository prendaRepository, ProveedorRepository proveedorRepository, PrendaMapper prendaMapper) {
        this.prendaRepository = prendaRepository;
        this.proveedorRepository = proveedorRepository;
        this.prendaMapper = prendaMapper;
    }

    public PrendaResponseDTO registrarPrenda(PrendaRequestDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(dto.proveedorId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el proveedor con ID: " + dto.proveedorId()));

        Prenda prenda = prendaMapper.dtoToEntity(dto, proveedor);
        Prenda prendaGuardada = prendaRepository.save(prenda);
        return prendaMapper.entityToDTO(prendaGuardada);
    }

    public List<PrendaResponseDTO> verInventario() {
        return prendaRepository.findAll().stream()
                .map(prendaMapper::entityToDTO)
                .toList();
    }

    public PrendaResponseDTO buscarPorId(Long id) {
        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la prenda con ID: " + id));
        return prendaMapper.entityToDTO(prenda);
    }

    public PrendaResponseDTO actualizarPrenda(Long id, PrendaRequestDTO dto) {
        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la prenda con ID: " + id));

        Proveedor proveedor = proveedorRepository.findById(dto.proveedorId())
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el proveedor con ID: " + dto.proveedorId()));

        prenda.setNombre(dto.nombre());
        prenda.setCategoria(dto.categoria());
        prenda.setTalle(dto.talle());
        prenda.setPrecio(dto.precio());
        prenda.setStock(dto.stock());
        prenda.setProveedor(proveedor);

        Prenda prendaActualizada = prendaRepository.save(prenda);
        return prendaMapper.entityToDTO(prendaActualizada);
    }

    public void eliminarPrenda(Long id) {
        if (!prendaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe la prenda con ID: " + id);
        }
        prendaRepository.deleteById(id);
    }
}