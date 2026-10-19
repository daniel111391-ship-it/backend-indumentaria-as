package com.indumentaria.stock.controller;

import com.indumentaria.stock.dto.PrendaDTO;
import com.indumentaria.stock.service.PrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {

    @Autowired
    private PrendaService prendaService;

    // 1. Ver todo el inventario
    @GetMapping("/all")
    public List<PrendaDTO> verInventario() {
        return prendaService.verInventario();
    }

    // 2. Registrar nueva prenda
    @PostMapping("/add")
    public String registrarPrenda(@RequestBody PrendaDTO dto) {
        return prendaService.registrarPrenda(dto);
    }

    // 3. Buscar por ID
    @GetMapping("/buscar/{id}")
    public PrendaDTO buscarPorId(@PathVariable Long id) {
        return prendaService.buscarPorId(id);
    }

    // 4. Modificar/Actualizar una prenda existente por su ID
    @PutMapping("/modificar/{id}")
    public String actualizarPrenda(@PathVariable Long id, @RequestBody PrendaDTO dto) {
        return prendaService.actualizarPrenda(id, dto);
    }

    // 5. Eliminar una prenda por su ID
    @DeleteMapping("/{id}")
    public String eliminarPrenda(@PathVariable Long id) {
        return prendaService.eliminarPrenda(id);
    }
}