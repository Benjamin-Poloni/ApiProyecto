package com.benjaminpoloni.apiproyecto.controller;


import com.benjaminpoloni.apiproyecto.model.Proyect;
import com.benjaminpoloni.apiproyecto.service.ProyectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/proyecto")
public class ProyectController {
    private final ProyectService proyectService;
    public ProyectController(ProyectService proyectService) {
        this.proyectService = proyectService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Proyect proyect = proyectService.findById(id);
            return ResponseEntity.ok(proyect);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("pong");
    }
}
