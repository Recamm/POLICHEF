package com.polichef.polichefApi.polichef.controller;

import com.polichef.polichefApi.polichef.dto.PlatoDTO;
import com.polichef.polichefApi.polichef.dto.TopPlatoDTO;
import com.polichef.polichefApi.polichef.model.Plato;
import com.polichef.polichefApi.polichef.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @PostMapping("/admin/platos")
    public ResponseEntity<?> crearPlato(@RequestBody PlatoDTO platoDTO) {
        try {
            Plato plato = platoService.crearPlato(platoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(plato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear plato: " + e.getMessage());
        }
    }

    @PutMapping("/admin/platos/{id}")
    public ResponseEntity<?> actualizarPlato(@PathVariable Integer id, @RequestBody PlatoDTO platoDTO) {
        try {
            Plato plato = platoService.actualizarPlato(id, platoDTO);
            return ResponseEntity.ok(plato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar plato: " + e.getMessage());
        }
    }

    @DeleteMapping("/admin/platos/{id}")
    public ResponseEntity<?> eliminarPlato(@PathVariable Integer id) {
        try {
            platoService.eliminarPlato(id);
            return ResponseEntity.ok("Plato eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar plato: " + e.getMessage());
        }
    }

    @GetMapping("/platos")
    public ResponseEntity<List<Plato>> obtenerTodosLosPlatos() {
        List<Plato> platos = platoService.obtenerTodosLosPlatos();
        return ResponseEntity.ok(platos);
    }

    @GetMapping("/platos/{id}")
    public ResponseEntity<?> obtenerPlato(@PathVariable Integer id) {
        try {
            Plato plato = platoService.obtenerPlato(id);
            return ResponseEntity.ok(plato);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Plato no encontrado: " + e.getMessage());
        }
    }

    @GetMapping("/estadisticas/top-platos")
    public ResponseEntity<?> obtenerTopPlatos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        try {
            List<TopPlatoDTO> topPlatos = platoService.obtenerTopPlatos(desde, hasta);
            return ResponseEntity.ok(topPlatos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener estadísticas: " + e.getMessage());
        }
    }
}