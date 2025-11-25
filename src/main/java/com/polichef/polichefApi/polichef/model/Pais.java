package com.polichef.polichefApi.polichef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Pais")
@JsonIgnoreProperties({"nochesTematicas"})
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nombre;

    @OneToMany(mappedBy = "pais")
    private List<NocheTematica> nochesTematicas;

    public Pais() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<NocheTematica> getNochesTematicas() { return nochesTematicas; }
    public void setNochesTematicas(List<NocheTematica> nochesTematicas) {
        this.nochesTematicas = nochesTematicas;
    }
}