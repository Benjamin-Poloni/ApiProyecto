package com.benjaminpoloni.apiproyecto.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tenologia")
public class Tech {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String habilidad;
    private String icon;

}
