package com.polichef.polichefApi.polichef.model;

import jakarta.persistence.*;

@Entity
@Table(name = "StockAlert")
public class StockAlert {
    @Id
    @ManyToOne
    @JoinColumn(name = "idIngrediente", nullable = false)
    private Ingrediente ingrediente;

    public StockAlert() {}

    public Ingrediente getIngrediente() { return ingrediente; }
    public void setIngrediente(Ingrediente ingrediente) { this.ingrediente = ingrediente; }
}