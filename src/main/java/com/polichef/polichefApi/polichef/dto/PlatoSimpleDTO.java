package com.polichef.polichefApi.polichef.dto;

public class PlatoSimpleDTO {
    private Integer id;
    private String nombre;
    private Integer valor;
    private String descripcion;

    public PlatoSimpleDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getValor() { return valor; }
    public void setValor(Integer valor) { this.valor = valor; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}