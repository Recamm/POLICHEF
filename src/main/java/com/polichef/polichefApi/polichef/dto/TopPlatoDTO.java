package com.polichef.polichefApi.polichef.dto;

public class TopPlatoDTO {
    private String nombre;
    private Long vecesPedido;

    public TopPlatoDTO(String nombre, Long vecesPedido) {
        this.nombre = nombre;
        this.vecesPedido = vecesPedido;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Long getVecesPedido() { return vecesPedido; }
    public void setVecesPedido(Long vecesPedido) { this.vecesPedido = vecesPedido; }
}