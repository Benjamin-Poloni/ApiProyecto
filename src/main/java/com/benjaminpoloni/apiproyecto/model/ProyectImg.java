package com.benjaminpoloni.apiproyecto.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "proyecto_imagen")
public class ProyectImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "proyecto_id")
    private Proyect proyecto;

}
