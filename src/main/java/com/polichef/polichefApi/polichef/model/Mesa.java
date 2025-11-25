package com.polichef.polichefApi.polichef.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer cantPersonas;

    public Mesa() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCantPersonas() { return cantPersonas; }
    public void setCantPersonas(Integer cantPersonas) { this.cantPersonas = cantPersonas; }
}