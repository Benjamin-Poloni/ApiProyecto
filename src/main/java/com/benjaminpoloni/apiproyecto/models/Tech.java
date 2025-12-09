package com.benjaminpoloni.apiproyecto.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tecnologia")
public class Tech {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String habilidad;

    @ToString.Exclude
    private String icon;

    @ManyToMany(mappedBy = "tech")
    @JsonIgnore
    private List<Proyect> proyects;


}
