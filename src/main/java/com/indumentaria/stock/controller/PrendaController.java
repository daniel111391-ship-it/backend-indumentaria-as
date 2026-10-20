package com.indumentaria.stock.controller;

import com.indumentaria.stock.dto.PrendaRequestDTO;
import com.indumentaria.stock.dto.PrendaResponseDTO;
import com.indumentaria.stock.service.PrendaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prendas") // Rutas REST limpias sin /all o /add
public class PrendaController {

    private final PrendaService prendaService;

    // Inyección por constructor
    public PrendaController(PrendaService prendaService) {
        this.prendaService = prendaService;
    }

    @GetMapping
    public ResponseEntity<List<PrendaResponseDTO>> verInventario() {
        return ResponseEntity.ok(prendaService.verInventario()); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrendaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prendaService.buscarPorId(id)); // 200 OK
    }

    @PostMapping
    public ResponseEntity<PrendaResponseDTO> registrarPrenda(@Valid @RequestBody PrendaRequestDTO dto) {
        PrendaResponseDTO nuevaPrenda = prendaService.registrarPrenda(dto);
        return new ResponseEntity<>(nuevaPrenda, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrendaResponseDTO> actualizarPrenda(@PathVariable Long id, @Valid @RequestBody PrendaRequestDTO dto) {
        return ResponseEntity.ok(prendaService.actualizarPrenda(id, dto)); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrenda(@PathVariable Long id) {
        prendaService.eliminarPrenda(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}