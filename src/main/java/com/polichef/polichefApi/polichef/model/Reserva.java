package com.polichef.polichefApi.polichef.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate fechaQueSeReservo;

    @Column(nullable = false)
    private LocalDate fechaReserva;

    @OneToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCalificacion", nullable = true)
    private Calificacion calificacion;

    public Reserva() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getFechaQueSeReservo() { return fechaQueSeReservo; }
    public void setFechaQueSeReservo(LocalDate fechaQueSeReservo) {
        this.fechaQueSeReservo = fechaQueSeReservo;
    }

    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Calificacion getCalificacion() { return calificacion; }
    public void setCalificacion(Calificacion calificacion) { this.calificacion = calificacion; }
}
