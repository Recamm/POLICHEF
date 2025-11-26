package com.polichef.polichefApi.polichef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Pedido")
@JsonIgnoreProperties({"platos", "reserva"})
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dniCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;

    @Column(name = "cantPersonas", nullable = false)
    private Integer personas;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PlatosDelPedido> platos;

    @OneToOne(mappedBy = "pedido")
    private Reserva reserva;

    public Pedido() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Sucursal getSucursal() { return sucursal; }
    public void setSucursal(Sucursal sucursal) { this.sucursal = sucursal; }

    public List<PlatosDelPedido> getPlatos() { return platos; }
    public void setPlatos(List<PlatosDelPedido> platos) { this.platos = platos; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public Integer getPersonas() { return personas; }
    public void setPersonas(Integer personas) { this.personas = personas; }
}