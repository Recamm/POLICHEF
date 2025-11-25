package com.polichef.polichefApi.polichef.dto;

public class CalificacionDTO {
    private Integer puntaje;
    private String descripcion;

    public CalificacionDTO() {}

    public Integer getPuntaje() { return puntaje; }
    public void setPuntaje(Integer puntaje) { this.puntaje = puntaje; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
