package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}