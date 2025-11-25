package com.polichef.polichefApi.polichef.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredientesDePlatos")
public class IngredientesDePlatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idIngrediente", nullable = false)
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "idPlato", nullable = false)
    private Plato plato;

    public IngredientesDePlatos() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Ingrediente getIngrediente() { return ingrediente; }
    public void setIngrediente(Ingrediente ingrediente) { this.ingrediente = ingrediente; }

    public Plato getPlato() { return plato; }
    public void setPlato(Plato plato) { this.plato = plato; }
}