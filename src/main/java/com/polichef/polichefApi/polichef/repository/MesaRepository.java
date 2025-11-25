package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    List<Mesa> findByCantPersonasGreaterThanEqual(Integer cantPersonas);
}