package com.polichef.polichefApi.polichef.controller;

import com.polichef.polichefApi.polichef.dto.ClienteDTO;
import com.polichef.polichefApi.polichef.dto.LoginDTO;
import com.polichef.polichefApi.polichef.dto.LoginResponseDTO;
import com.polichef.polichefApi.polichef.model.Cliente;
import com.polichef.polichefApi.polichef.repository.ClienteRepository;
import com.polichef.polichefApi.polichef.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente cliente = clienteService.registrarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Integer dni) {
        try {
            Cliente cliente = clienteService.obtenerCliente(dni);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosUsuarios() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Optional<Cliente> clienteOpt = clienteService.obtenerPorEmail(loginDTO.getEmail());

            if (clienteOpt.isEmpty()) {
                LoginResponseDTO response = new LoginResponseDTO(false, "Email no registrado");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            Cliente cliente = clienteOpt.get();

            if (!cliente.getContrasena().equals(loginDTO.getPassword())) {
                LoginResponseDTO response = new LoginResponseDTO(false, "Contraseña incorrecta");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            LoginResponseDTO response = new LoginResponseDTO(true, "Login exitoso");
            response.setDni(cliente.getDni());
            response.setNombre(cliente.getNombre());
            response.setApellido(cliente.getApellido());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponseDTO(false, "Error en el servidor: " + e.getMessage()));
        }
    }
}