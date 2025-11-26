package com.polichef.polichefApi.polichef.service;

import com.polichef.polichefApi.polichef.dto.PedidoDTO;
import com.polichef.polichefApi.polichef.dto.PlatoDelPedidoDTO;
import com.polichef.polichefApi.polichef.model.*;
import com.polichef.polichefApi.polichef.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private PlatoRepository platoRepository;

    @Transactional
    public Pedido crearPedido(PedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById(pedidoDTO.getDniCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Sucursal sucursal = sucursalRepository.findById(pedidoDTO.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setSucursal(sucursal);
        pedido.setPersonas(pedidoDTO.getCantPersonas());

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        if (pedidoDTO.getPlatos() != null) {
            List<PlatosDelPedido> platosList = new ArrayList<>();
            for (PlatoDelPedidoDTO platoDTO : pedidoDTO.getPlatos()) {
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

        return pedidoRepository.save(pedidoGuardado);
    }

    public Pedido obtenerPedido(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }
}