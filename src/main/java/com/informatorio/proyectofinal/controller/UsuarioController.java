package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.service.UsuarioService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<?> getUsuario(@RequestParam(required = false) String ciudad,
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeCreacion) {
        if (ciudad == null && fechaDeCreacion == null) {
            return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
        } else if (ciudad != null) {
            return new ResponseEntity<>(usuarioService.getUsuariosByCiudad(ciudad), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(usuarioService.getUsuariosPorFecha(fechaDeCreacion), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long id) {
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}