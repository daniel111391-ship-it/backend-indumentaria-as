package com.indumentaria.stock.controller;

import com.indumentaria.stock.dto.PrendaResponseDTO;
import com.indumentaria.stock.dto.ProveedorRequestDTO;
import com.indumentaria.stock.dto.ProveedorResponseDTO;
import com.indumentaria.stock.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> listarProveedores() {
        return ResponseEntity.ok(proveedorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.buscarPorId(id));
    }

    @GetMapping("/{id}/prendas")
    public ResponseEntity<List<PrendaResponseDTO>> obtenerPrendasPorProveedor(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerPrendasPorProveedor(id));
    }

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> crearProveedor(@Valid @RequestBody ProveedorRequestDTO dto) {
        ProveedorResponseDTO nuevo = proveedorService.crearProveedor(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> actualizarProveedor(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorRequestDTO dto
    ) {
        return ResponseEntity.ok(proveedorService.actualizarProveedor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}