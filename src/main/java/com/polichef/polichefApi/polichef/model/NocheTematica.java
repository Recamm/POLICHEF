package com.polichef.polichefApi.polichef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "NocheTematica")
@JsonIgnoreProperties({"platos"})
public class NocheTematica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idPais", nullable = false)
    private Pais pais;

    @OneToMany(mappedBy = "nocheTematica")
    private List<Plato> platos;

    public NocheTematica() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }

    public List<Plato> getPlatos() { return platos; }
    public void setPlatos(List<Plato> platos) { this.platos = platos; }
}