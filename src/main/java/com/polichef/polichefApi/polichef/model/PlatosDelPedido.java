package com.polichef.polichefApi.polichef.model;

import jakarta.persistence.*;

@Entity
@Table(name = "platosDelPedido")
public class PlatosDelPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idPlato", nullable = false)
    private Plato plato;

    @Column(nullable = false)
    private Integer cantidad;

    public PlatosDelPedido() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Plato getPlato() { return plato; }
    public void setPlato(Plato plato) { this.plato = plato; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
