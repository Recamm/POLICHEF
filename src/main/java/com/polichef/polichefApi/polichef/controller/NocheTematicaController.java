package com.polichef.polichefApi.polichef.controller;

import com.polichef.polichefApi.polichef.dto.NocheTematicaResponseDTO;
import com.polichef.polichefApi.polichef.model.NocheTematica;
import com.polichef.polichefApi.polichef.service.NocheTematicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class NocheTematicaController {

    @Autowired
    private NocheTematicaService nocheTematicaService;

    @GetMapping("/tematicas")
    public ResponseEntity<List<NocheTematicaResponseDTO>> obtenerCalendarioTematicas() {
        List<NocheTematicaResponseDTO> noches = nocheTematicaService.obtenerCalendarioTematicas();
        return ResponseEntity.ok(noches);
    }

    @GetMapping("/tematicas/rango")
    public ResponseEntity<List<NocheTematicaResponseDTO>> obtenerTematicasPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<NocheTematicaResponseDTO> noches = nocheTematicaService.obtenerTematicasPorRango(inicio, fin);
        return ResponseEntity.ok(noches);
    }

    @GetMapping("/tematicas/todas")
    public ResponseEntity<List<NocheTematica>> obtenerTodasLasNoches() {
        List<NocheTematica> noches = nocheTematicaService.obtenerTodasLasNoches();
        return ResponseEntity.ok(noches);
    }

    @GetMapping("/tematicas/semana-actual")
    public ResponseEntity<?> obtenerTematicaSemanaActual() {
        Optional<NocheTematicaResponseDTO> tematica = nocheTematicaService.obtenerTematicaSemanaActual();

        if (tematica.isPresent()) {
            return ResponseEntity.ok(tematica.get());
        } else {
            return ResponseEntity.ok().body(null);
        }
    }
}