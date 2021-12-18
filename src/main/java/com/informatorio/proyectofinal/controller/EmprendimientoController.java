package com.informatorio.proyectofinal.controller;

import com.informatorio.proyectofinal.dto.OperacionEmprendimiento;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.service.EmprendimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/emprendimiento")
public class EmprendimientoController {

    private final EmprendimientoService emprendimientoService;

    public EmprendimientoController(EmprendimientoService emprendimientoService) {
        this.emprendimientoService = emprendimientoService;
    }

    @PostMapping
    public ResponseEntity<?> createEmprendimiento(@Valid @RequestBody OperacionEmprendimiento operacionEmprendimiento) {
        return new ResponseEntity<>(emprendimientoService.createEmprendimiento(operacionEmprendimiento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getEmprendimiento(@RequestParam(required = false) String tagName, Boolean publicado) {
        if (tagName == null && publicado == null) {
            return new ResponseEntity<>(emprendimientoService.getEmprendimientos(), HttpStatus.OK);
        } else if (tagName != null){
            return new ResponseEntity<>(emprendimientoService.getEmprendimientosByTag(tagName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(emprendimientoService.getEmprendimientosNoPublicados(publicado), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmprendimiento(@Valid @RequestBody Emprendimiento emprendimiento, @PathVariable Long id) {
        return  emprendimientoService.updateEmprendimiento(id, emprendimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmprendimiento(@PathVariable Long id) {
        emprendimientoService.deleteEmprendimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}