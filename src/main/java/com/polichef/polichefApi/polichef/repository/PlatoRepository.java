package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {

    List<Plato> findByNocheTematicaId(Integer nocheTematicaId);

    @Query(value = "SELECT p.nombre as Nombre, COUNT(pd.idPlato) as VecesPedido " +
            "FROM platosDelPedido pd " +
            "JOIN Pedido ped ON pd.idPedido = ped.id " +
            "JOIN Reserva r ON r.idPedido = ped.id " +
            "JOIN Plato p ON p.id = pd.idPlato " +
            "WHERE r.fechaReserva > :fechaInicio AND r.fechaReserva < :fechaFin " +
            "GROUP BY p.id, p.nombre " +
            "ORDER BY VecesPedido DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Object[]> findTop3PlatosMasPedidos(@Param("fechaInicio") LocalDate fechaInicio,
                                            @Param("fechaFin") LocalDate fechaFin);
}