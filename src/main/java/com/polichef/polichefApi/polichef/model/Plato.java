package com.polichef.polichefApi.polichef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Plato")
@JsonIgnoreProperties({"ingredientes", "preferencias", "pedidos"})
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nombre;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idNocheTematica", nullable = false)
    private NocheTematica nocheTematica;

    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL)
    private List<IngredientesDePlatos> ingredientes;

    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL)
    private List<PreferenciasAlimenticiasDelPlato> preferencias;

    @OneToMany(mappedBy = "plato")
    private List<PlatosDelPedido> pedidos;

    public Plato() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getValor() { return valor; }
    public void setValor(Integer valor) { this.valor = valor; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public NocheTematica getNocheTematica() { return nocheTematica; }
    public void setNocheTematica(NocheTematica nocheTematica) {
        this.nocheTematica = nocheTematica;
    }

    public List<IngredientesDePlatos> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<IngredientesDePlatos> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<PreferenciasAlimenticiasDelPlato> getPreferencias() { return preferencias; }
    public void setPreferencias(List<PreferenciasAlimenticiasDelPlato> preferencias) {
        this.preferencias = preferencias;
    }

    public List<PlatosDelPedido> getPedidos() { return pedidos; }
    public void setPedidos(List<PlatosDelPedido> pedidos) { this.pedidos = pedidos; }
}
