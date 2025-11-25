package com.polichef.polichefApi.polichef.service;

import com.polichef.polichefApi.polichef.dto.ClienteDTO;
import com.polichef.polichefApi.polichef.model.*;
import com.polichef.polichefApi.polichef.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PreferenciaAlimenticiaRepository preferenciaRepository;

    @Transactional
    public Cliente registrarCliente(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setDni(clienteDTO.getDni());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setContrasena(clienteDTO.getContrasena()); // Guardar contraseña (en producción usar BCrypt)
        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setCuentaCreada(LocalDate.now());
        cliente.setPuntos(0);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        if (clienteDTO.getPreferenciasAlimenticias() != null) {
            for (Integer idPreferencia : clienteDTO.getPreferenciasAlimenticias()) {
                PreferenciaAlimenticia preferencia = preferenciaRepository.findById(idPreferencia)
                        .orElseThrow(() -> new RuntimeException("Preferencia no encontrada: " + idPreferencia));

                PreferenciasAlimenticiasDelCliente pac = new PreferenciasAlimenticiasDelCliente();
                pac.setCliente(clienteGuardado);
                pac.setPreferenciaAlimenticia(preferencia);
            }
        }

        return clienteGuardado;
    }

    public Cliente obtenerCliente(Integer dni) {
        return clienteRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}