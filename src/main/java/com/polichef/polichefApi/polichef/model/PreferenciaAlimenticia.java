package com.polichef.polichefApi.polichef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PreferenciaAlimenticia")
@JsonIgnoreProperties({"platos", "clientes"})
public class PreferenciaAlimenticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "preferenciaAlimenticia")
    private List<PreferenciasAlimenticiasDelPlato> platos;

    @OneToMany(mappedBy = "preferenciaAlimenticia")
    private List<PreferenciasAlimenticiasDelCliente> clientes;

    public PreferenciaAlimenticia() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<PreferenciasAlimenticiasDelPlato> getPlatos() { return platos; }
    public void setPlatos(List<PreferenciasAlimenticiasDelPlato> platos) {
        this.platos = platos;
    }

    public List<PreferenciasAlimenticiasDelCliente> getClientes() { return clientes; }
    public void setClientes(List<PreferenciasAlimenticiasDelCliente> clientes) {
        this.clientes = clientes;
    }
}