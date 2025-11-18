package com.benjaminpoloni.apiproyecto.service;

import com.benjaminpoloni.apiproyecto.model.Proyect;
import com.benjaminpoloni.apiproyecto.repository.ProyectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProyectService {

    private final ProyectRepository proyectRepository;



    public List<Proyect> findAll() {
        return proyectRepository.findAll();
    }

    public Proyect findById(Long id) {
        return proyectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto not found:" + id));
    }

    public Proyect findByName(String name) {
        return proyectRepository.findByNombreIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto not found:" + name));
    }
}
