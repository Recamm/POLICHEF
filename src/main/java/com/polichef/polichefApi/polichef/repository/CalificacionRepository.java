package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
}