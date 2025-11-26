package com.polichef.polichefApi.polichef.service;

import com.polichef.polichefApi.polichef.dto.CalificacionDTO;
import com.polichef.polichefApi.polichef.dto.ReservaDTO;
import com.polichef.polichefApi.polichef.dto.PlatoDelPedidoDTO;
import com.polichef.polichefApi.polichef.model.*;
import com.polichef.polichefApi.polichef.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Transactional
    public Reserva crearReserva(ReservaDTO reservaDTO) {
        Cliente cliente = clienteRepository.findById(reservaDTO.getDniCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Sucursal sucursal = sucursalRepository.findById(reservaDTO.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setSucursal(sucursal);
        pedido.setPersonas(reservaDTO.getCantPersonas());
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        if (reservaDTO.getPlatos() != null && !reservaDTO.getPlatos().isEmpty()) {
            List<PlatosDelPedido> platosList = new ArrayList<>();
            for (PlatoDelPedidoDTO platoDTO : reservaDTO.getPlatos()) {
                Plato plato = platoRepository.findById(platoDTO.getIdPlato())
                        .orElseThrow(() -> new RuntimeException("Plato no encontrado: " + platoDTO.getIdPlato()));

                PlatosDelPedido pdp = new PlatosDelPedido();
                pdp.setPedido(pedidoGuardado);
                pdp.setPlato(plato);
                pdp.setCantidad(platoDTO.getCantidad());
                platosList.add(pdp);
            }
            pedidoGuardado.setPlatos(platosList);
        }

        Calificacion calificacion = new Calificacion();
        calificacion.setPuntaje(0);
        calificacion.setDescripcion("Pendiente");
        Calificacion calificacionGuardada = calificacionRepository.save(calificacion);

        Reserva reserva = new Reserva();
        reserva.setPedido(pedidoGuardado);
        reserva.setFechaQueSeReservo(LocalDate.now());
        reserva.setFechaReserva(reservaDTO.getFechaReserva());
        reserva.setCalificacion(calificacionGuardada);

        return reservaRepository.save(reserva);
    }

    @Transactional
    public Reserva actualizarReserva(Integer id, ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (reservaDTO.getFechaReserva() != null) {
            reserva.setFechaReserva(reservaDTO.getFechaReserva());
        }

        return reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarReserva(Integer id) {
        if (!reservaRepository.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada");
        }
        reservaRepository.deleteById(id);
    }

    @Transactional
    public Reserva calificarReserva(Integer id, CalificacionDTO calificacionDTO) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (calificacionDTO.getPuntaje() < 1 || calificacionDTO.getPuntaje() > 5) {
            throw new RuntimeException("El puntaje debe estar entre 1 y 5");
        }

        Calificacion calificacion = new Calificacion(calificacionDTO.getPuntaje(),calificacionDTO.getDescripcion());
        reserva.setCalificacion(calificacion);

        calificacionRepository.save(calificacion);
        reservaRepository.save(reserva);

        if (calificacionDTO.getPuntaje() >= 4) {
            Cliente cliente = reserva.getPedido().getCliente();
            cliente.setPuntos(cliente.getPuntos() + 10);
            clienteRepository.save(cliente);
        }

        return reserva;
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerReserva(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    public List<Reserva> obtenerReservasPorCliente(Integer dniCliente) {
        return reservaRepository.findByClienteDni(dniCliente);
    }

}