package com.indumentaria.stock.service;

import com.indumentaria.stock.RecursoNoEncontradoException;
import com.indumentaria.stock.dto.PrendaResponseDTO;
import com.indumentaria.stock.dto.ProveedorRequestDTO;
import com.indumentaria.stock.dto.ProveedorResponseDTO;
import com.indumentaria.stock.entity.Proveedor;
import com.indumentaria.stock.mapper.PrendaMapper;
import com.indumentaria.stock.mapper.ProveedorMapper;
import com.indumentaria.stock.repository.PrendaRepository;
import com.indumentaria.stock.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final PrendaRepository prendaRepository;
    private final ProveedorMapper proveedorMapper;
    private final PrendaMapper prendaMapper;

    public ProveedorService(
            ProveedorRepository proveedorRepository,
            PrendaRepository prendaRepository,
            ProveedorMapper proveedorMapper,
            PrendaMapper prendaMapper
    ) {
        this.proveedorRepository = proveedorRepository;
        this.prendaRepository = prendaRepository;
        this.proveedorMapper = proveedorMapper;
        this.prendaMapper = prendaMapper;
    }

    public List<ProveedorResponseDTO> listarTodos() {
        return proveedorRepository.findAll().stream()
                .map(proveedorMapper::entityToDTO)
                .toList();
    }

    public ProveedorResponseDTO buscarPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el proveedor con ID: " + id));
        return proveedorMapper.entityToDTO(proveedor);
    }

    public ProveedorResponseDTO crearProveedor(ProveedorRequestDTO dto) {
        Proveedor proveedor = proveedorMapper.dtoToEntity(dto);
        Proveedor guardado = proveedorRepository.save(proveedor);
        return proveedorMapper.entityToDTO(guardado);
    }

    public ProveedorResponseDTO actualizarProveedor(Long id, ProveedorRequestDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el proveedor con ID: " + id));

        proveedor.setNombreContacto(dto.nombreContacto());
        proveedor.setNombreEmpresa(dto.nombreEmpresa());
        proveedor.setTelefono(dto.telefono());

        Proveedor actualizado = proveedorRepository.save(proveedor);
        return proveedorMapper.entityToDTO(actualizado);
    }

    public void eliminarProveedor(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe el proveedor con ID: " + id);
        }
        proveedorRepository.deleteById(id);
    }

    // Consulta por relación pedida por el profesor
    public List<PrendaResponseDTO> obtenerPrendasPorProveedor(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe el proveedor con ID: " + id);
        }
        return prendaRepository.findByProveedorId(id).stream()
                .map(prendaMapper::entityToDTO)
                .toList();
    }
}