package com.benjaminpoloni.apiproyecto.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "proyecto")
@Data
public class Proyect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(length = 500, name = "card_description")
    private String cardDescription;

    @Column(name = "repo_link")
    private String repoLink;


    private String status;
    private String category;


    @ManyToMany
    @JoinTable(
            name = "proyecto_tech",
            joinColumns = @JoinColumn(name = "proyecto"),
            inverseJoinColumns = @JoinColumn(name ="tech")
    )
    private List<Tech> tech;


    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProyectImg> proyectoImagen;


}
