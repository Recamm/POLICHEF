package com.polichef.polichefApi.polichef.controller;

import com.polichef.polichefApi.polichef.model.Sucursal;
import com.polichef.polichefApi.polichef.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "*")
public class SucursalController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @GetMapping
    public ResponseEntity<List<Sucursal>> obtenerTodasLasSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSucursal(@PathVariable Integer id) {
        return sucursalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}