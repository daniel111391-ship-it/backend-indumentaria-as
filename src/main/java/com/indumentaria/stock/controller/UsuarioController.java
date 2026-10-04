package com.indumentaria.stock.controller;

import com.indumentaria.stock.dto.UsuarioDTO;
import com.indumentaria.stock.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Ruta para crear un usuario: POST a http://localhost:8080/api/usuarios/add
    @PostMapping("/add")
    public String registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.crearUsuario(usuarioDTO);
    }

    // Ruta para listar usuarios: GET a http://localhost:8080/api/usuarios/all
    @GetMapping("/all")
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.obtenerTodos();
    }
}