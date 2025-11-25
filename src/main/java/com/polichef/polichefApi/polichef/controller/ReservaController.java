package com.polichef.polichefApi.polichef.controller;

import com.polichef.polichefApi.polichef.dto.CalificacionDTO;
import com.polichef.polichefApi.polichef.dto.ReservaDTO;
import com.polichef.polichefApi.polichef.model.Reserva;
import com.polichef.polichefApi.polichef.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            Reserva reserva = reservaService.crearReserva(reservaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear reserva: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(@PathVariable Integer id, @RequestBody ReservaDTO reservaDTO) {
        try {
            Reserva reserva = reservaService.actualizarReserva(id, reservaDTO);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar reserva: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarReserva(@PathVariable Integer id) {
        try {
            reservaService.cancelarReserva(id);
            return ResponseEntity.ok("Reserva cancelada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al cancelar reserva: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/calificar")
    public ResponseEntity<?> calificarReserva(@PathVariable Integer id, @RequestBody CalificacionDTO calificacionDTO) {
        try {
            Reserva reserva = reservaService.calificarReserva(id, calificacionDTO);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al calificar reserva: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservaService.obtenerTodasLasReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerReserva(@PathVariable Integer id) {
        try {
            Reserva reserva = reservaService.obtenerReserva(id);
            return ResponseEntity.ok(reserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reserva no encontrada: " + e.getMessage());
        }
    }

    @GetMapping("/cliente/{dniCliente}")
    public ResponseEntity<List<Reserva>> obtenerReservasPorCliente(@PathVariable Integer dniCliente) {
        List<Reserva> reservas = reservaService.obtenerReservasPorCliente(dniCliente);
        return ResponseEntity.ok(reservas);
    }
}