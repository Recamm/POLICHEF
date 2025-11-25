package com.polichef.polichefApi.polichef.repository;

import com.polichef.polichefApi.polichef.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
}