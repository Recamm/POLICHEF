package com.polichef.polichefApi.polichef.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PreferenciasAlimenticiasDelPlato")
public class PreferenciasAlimenticiasDelPlato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idPreferenciasAlimenticias", nullable = false)
    private PreferenciaAlimenticia preferenciaAlimenticia;

    @ManyToOne
    @JoinColumn(name = "idPlato", nullable = false)
    private Plato plato;

    public PreferenciasAlimenticiasDelPlato() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public PreferenciaAlimenticia getPreferenciaAlimenticia() {
        return preferenciaAlimenticia;
    }
    public void setPreferenciaAlimenticia(PreferenciaAlimenticia preferenciaAlimenticia) {
        this.preferenciaAlimenticia = preferenciaAlimenticia;
    }

    public Plato getPlato() { return plato; }
    public void setPlato(Plato plato) { this.plato = plato; }
}