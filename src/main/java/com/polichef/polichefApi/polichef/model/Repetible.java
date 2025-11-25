package com.polichef.polichefApi.polichef.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Repetible")
public class Repetible {
    @Id
    @OneToOne
    @JoinColumn(name = "idRepetible", nullable = false)
    private Reserva reserva;

    @Column(nullable = false)
    private Boolean repetible;

    public Repetible() {}

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public Boolean getRepetible() { return repetible; }
    public void setRepetible(Boolean repetible) { this.repetible = repetible; }
}