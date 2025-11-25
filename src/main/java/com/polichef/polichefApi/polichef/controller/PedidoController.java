package com.polichef.polichefApi.polichef.controller;

import com.polichef.polichefApi.polichef.dto.PedidoDTO;
import com.polichef.polichefApi.polichef.model.Pedido;
import com.polichef.polichefApi.polichef.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        try {
            Pedido pedido = pedidoService.crearPedido(pedidoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear pedido: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPedido(@PathVariable Integer id) {
        try {
            Pedido pedido = pedidoService.obtenerPedido(id);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pedido no encontrado: " + e.getMessage());
        }
    }
}