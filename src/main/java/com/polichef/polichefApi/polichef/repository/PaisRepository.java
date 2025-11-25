package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    Optional<Pais> findByNombre(String nombre);
}