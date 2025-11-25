package com.polichef.polichefApi.polichef.dto;

public class PlatoDelPedidoDTO {
    private Integer idPlato;
    private Integer cantidad;

    public PlatoDelPedidoDTO() {}

    public Integer getIdPlato() { return idPlato; }
    public void setIdPlato(Integer idPlato) { this.idPlato = idPlato; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}