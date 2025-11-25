package com.polichef.polichefApi.polichef.dto;

import java.util.List;

public class PedidoDTO {
    private Integer dniCliente;
    private Integer idSucursal;
    private List<PlatoDelPedidoDTO> platos;

    public PedidoDTO() {}

    public Integer getDniCliente() { return dniCliente; }
    public void setDniCliente(Integer dniCliente) { this.dniCliente = dniCliente; }

    public Integer getIdSucursal() { return idSucursal; }
    public void setIdSucursal(Integer idSucursal) { this.idSucursal = idSucursal; }

    public List<PlatoDelPedidoDTO> getPlatos() { return platos; }
    public void setPlatos(List<PlatoDelPedidoDTO> platos) { this.platos = platos; }
}