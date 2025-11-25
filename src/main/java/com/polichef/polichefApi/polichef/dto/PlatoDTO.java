package com.polichef.polichefApi.polichef.dto;

import java.util.List;

public class PlatoDTO {
    private Integer id;
    private String nombre;
    private Integer valor;
    private String descripcion;
    private Integer idNocheTematica;
    private List<Integer> ingredientes;
    private List<Integer> preferencias;

    public PlatoDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getValor() { return valor; }
    public void setValor(Integer valor) { this.valor = valor; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getIdNocheTematica() { return idNocheTematica; }
    public void setIdNocheTematica(Integer idNocheTematica) { this.idNocheTematica = idNocheTematica; }

    public List<Integer> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<Integer> ingredientes) { this.ingredientes = ingredientes; }

    public List<Integer> getPreferencias() { return preferencias; }
    public void setPreferencias(List<Integer> preferencias) { this.preferencias = preferencias; }
}