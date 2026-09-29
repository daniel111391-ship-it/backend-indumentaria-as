package com.indumentaria.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {

    @Autowired
    private PrendaRepository prendaRepository;

    // 1. Ver todo el inventario (ahora incluye el ID)
    @GetMapping("/all")
    public List<PrendaDTO> verInventario() {
        return prendaRepository.findAll().stream()
                .map(p -> new PrendaDTO(
                        p.getId(),          // <-- 1. Agregado: el ID de la entidad
                        p.getNombre(),
                        p.getCategoria(),
                        p.getTalle(),
                        p.getPrecio(),
                        p.getStock()
                ))
                .toList();
    }

    // 2. Registrar nueva prenda
    @PostMapping("/add")
    public String registrarPrenda(@RequestBody PrendaDTO dto) {
        Prenda nuevaPrenda = new Prenda();
        // Nota: no asignamos ID aquí porque la base de datos lo genera de forma automática
        nuevaPrenda.setNombre(dto.nombre());
        nuevaPrenda.setCategoria(dto.categoria());
        nuevaPrenda.setTalle(dto.talle());
        nuevaPrenda.setPrecio(dto.precio());
        nuevaPrenda.setStock(dto.stock());

        prendaRepository.save(nuevaPrenda);

        return "¡Prenda registrada con éxito en el sistema!";
    }

    // 3. Modificar/Actualizar una prenda existente por su ID
    @PutMapping("/{id}")
    public String actualizarPrenda(@PathVariable Long id, @RequestBody PrendaDTO dto) {
        return prendaRepository.findById(id).map(prenda -> {
            prenda.setNombre(dto.nombre());
            prenda.setCategoria(dto.categoria());
            prenda.setTalle(dto.talle());
            prenda.setPrecio(dto.precio());
            prenda.setStock(dto.stock());
            prendaRepository.save(prenda);
            return "¡Prenda con ID " + id + " actualizada con éxito!";
        }).orElse("Error: No se encontró la prenda con ID " + id);
    }

    // 4. Eliminar una prenda por su ID
    @DeleteMapping("/{id}")
    public String eliminarPrenda(@PathVariable Long id) {
        if (prendaRepository.existsById(id)) {
            prendaRepository.deleteById(id);
            return "¡Prenda con ID " + id + " eliminada con éxito!";
        } else {
            return "Error: No se encontró la prenda con ID " + id;
        }
    }

    // Cumple requisito: Buscar por ID
    @GetMapping("/buscar/{id}")
    public PrendaDTO buscarPorId(@PathVariable Long id) {
        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la prenda con ID: " + id));

        return new PrendaDTO(prenda.getId(), prenda.getNombre(), prenda.getCategoria(), prenda.getTalle(), prenda.getPrecio(), prenda.getStock());
    }

    // Cumple requisito: Modificar (Actualizar)
    @PutMapping("/modificar/{id}")
    public String modificarPrenda(@PathVariable Long id, @RequestBody PrendaDTO dto) {
        Prenda prenda = prendaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la prenda con ID: " + id));

        prenda.setNombre(dto.nombre());
        prenda.setCategoria(dto.categoria());
        prenda.setTalle(dto.talle());
        prenda.setPrecio(dto.precio());
        prenda.setStock(dto.stock());

        prendaRepository.save(prenda);
        return "¡Prenda actualizada con éxito!";
    }
}