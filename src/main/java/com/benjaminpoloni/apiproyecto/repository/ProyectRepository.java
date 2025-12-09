package com.benjaminpoloni.apiproyecto.repository;

import com.benjaminpoloni.apiproyecto.models.Proyect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProyectRepository extends JpaRepository<Proyect, Long> {
    Optional<Proyect> findByNombreIgnoreCase(String nombre);
}
