package com.benjaminpoloni.apiproyecto.repository;

import com.benjaminpoloni.apiproyecto.model.Proyect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProyectRepository extends JpaRepository<Proyect, Long> {
    Optional<Proyect> findByNombreIgnoreCase(String nombre);
}
