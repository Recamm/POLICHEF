package com.polichef.polichefApi.polichef.controller;

import com.polichef.polichefApi.polichef.model.PreferenciaAlimenticia;
import com.polichef.polichefApi.polichef.repository.PreferenciaAlimenticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferencias")
@CrossOrigin(origins = "*")
public class PreferenciaController {

    @Autowired
    private PreferenciaAlimenticiaRepository preferenciaRepository;

    @GetMapping
    public ResponseEntity<List<PreferenciaAlimenticia>> obtenerTodasLasPreferencias() {
        List<PreferenciaAlimenticia> preferencias = preferenciaRepository.findAll();
        return ResponseEntity.ok(preferencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPreferencia(@PathVariable Integer id) {
        return preferenciaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}