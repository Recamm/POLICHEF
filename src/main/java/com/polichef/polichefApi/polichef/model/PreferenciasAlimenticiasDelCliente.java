package com.polichef.polichefApi.polichef.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PreferenciasAlimenticiasDelCliente")
public class PreferenciasAlimenticiasDelCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idPreferenciasAlimenticias", nullable = false)
    private PreferenciaAlimenticia preferenciaAlimenticia;

    @ManyToOne
    @JoinColumn(name = "dniCliente", nullable = false)
    private Cliente cliente;

    public PreferenciasAlimenticiasDelCliente() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public PreferenciaAlimenticia getPreferenciaAlimenticia() {
        return preferenciaAlimenticia;
    }
    public void setPreferenciaAlimenticia(PreferenciaAlimenticia preferenciaAlimenticia) {
        this.preferenciaAlimenticia = preferenciaAlimenticia;
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
