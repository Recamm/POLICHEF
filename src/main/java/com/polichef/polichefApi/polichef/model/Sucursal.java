package com.polichef.polichefApi.polichef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Sucursal")
@JsonIgnoreProperties({"pedidos"})
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String direccion;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String telefono;

    private LocalDate abiertaDesde;

    @OneToMany(mappedBy = "sucursal")
    private List<Pedido> pedidos;

    public Sucursal() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getAbiertaDesde() { return abiertaDesde; }
    public void setAbiertaDesde(LocalDate abiertaDesde) { this.abiertaDesde = abiertaDesde; }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}
