package com.polichef.polichefApi.polichef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ingrediente")
@JsonIgnoreProperties({"platosQueLoUsan"})
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nombre;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer minimoStock;

    @OneToMany(mappedBy = "ingrediente")
    private List<IngredientesDePlatos> platosQueLoUsan;

    public Ingrediente() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Integer getMinimoStock() { return minimoStock; }
    public void setMinimoStock(Integer minimoStock) { this.minimoStock = minimoStock; }

    public List<IngredientesDePlatos> getPlatosQueLoUsan() { return platosQueLoUsan; }
    public void setPlatosQueLoUsan(List<IngredientesDePlatos> platosQueLoUsan) {
        this.platosQueLoUsan = platosQueLoUsan;
    }
}