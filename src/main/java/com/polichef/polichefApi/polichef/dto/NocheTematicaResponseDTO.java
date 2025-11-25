package com.polichef.polichefApi.polichef.dto;

import java.time.LocalDate;
import java.util.List;

public class NocheTematicaResponseDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private LocalDate fecha;
    private String pais;
    private List<PlatoSimpleDTO> platos;

    public NocheTematicaResponseDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public List<PlatoSimpleDTO> getPlatos() { return platos; }
    public void setPlatos(List<PlatoSimpleDTO> platos) { this.platos = platos; }
}