package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.PreferenciaAlimenticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciaAlimenticiaRepository extends JpaRepository<PreferenciaAlimenticia, Integer> {
}