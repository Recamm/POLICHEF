package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.NocheTematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NocheTematicaRepository extends JpaRepository<NocheTematica, Integer> {
    List<NocheTematica> findByFechaAfter(LocalDate fecha);
    List<NocheTematica> findByFechaBetween(LocalDate inicio, LocalDate fin);

    Optional<NocheTematica> findByFecha(LocalDate fecha);
}