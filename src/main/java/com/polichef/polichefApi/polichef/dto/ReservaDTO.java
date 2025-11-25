package com.polichef.polichefApi.polichef.dto;

import java.time.LocalDate;
import java.util.List;

public class ReservaDTO {
    private Integer dniCliente;
    private Integer idSucursal;
    private LocalDate fechaReserva;
    private Integer cantPersonas;
    private List<PlatoDelPedidoDTO> platos;

    public ReservaDTO() {}

    public Integer getDniCliente() { return dniCliente; }
    public void setDniCliente(Integer dniCliente) { this.dniCliente = dniCliente; }

    public Integer getIdSucursal() { return idSucursal; }
    public void setIdSucursal(Integer idSucursal) { this.idSucursal = idSucursal; }

    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

    public Integer getCantPersonas() { return cantPersonas; }
    public void setCantPersonas(Integer cantPersonas) { this.cantPersonas = cantPersonas; }

    public List<PlatoDelPedidoDTO> getPlatos() { return platos; }
    public void setPlatos(List<PlatoDelPedidoDTO> platos) { this.platos = platos; }
}