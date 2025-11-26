package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByFechaReserva(LocalDate fechaReserva);

    @Query("SELECT r FROM Reserva r WHERE r.pedido.cliente.dni = :dniCliente")
    List<Reserva> findByClienteDni(Integer dniCliente);
}