package com.polichef.polichefApi.polichef.service;

import com.polichef.polichefApi.polichef.dto.PlatoDTO;
import com.polichef.polichefApi.polichef.dto.TopPlatoDTO;
import com.polichef.polichefApi.polichef.model.*;
import com.polichef.polichefApi.polichef.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private NocheTematicaRepository nocheTematicaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private PreferenciaAlimenticiaRepository preferenciaRepository;

    @Transactional
    public Plato crearPlato(PlatoDTO platoDTO) {
        NocheTematica nocheTematica = nocheTematicaRepository.findById(platoDTO.getIdNocheTematica())
                .orElseThrow(() -> new RuntimeException("Noche temática no encontrada"));

        Plato plato = new Plato();
        plato.setNombre(platoDTO.getNombre());
        plato.setValor(platoDTO.getValor());
        plato.setDescripcion(platoDTO.getDescripcion());
        plato.setNocheTematica(nocheTematica);

        Plato platoGuardado = platoRepository.save(plato);

        if (platoDTO.getIngredientes() != null) {
            List<IngredientesDePlatos> ingredientesList = new ArrayList<>();
            for (Integer idIngrediente : platoDTO.getIngredientes()) {
                Ingrediente ingrediente = ingredienteRepository.findById(idIngrediente)
                        .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado: " + idIngrediente));

                IngredientesDePlatos idp = new IngredientesDePlatos();
                idp.setPlato(platoGuardado);
                idp.setIngrediente(ingrediente);
                ingredientesList.add(idp);
            }
            platoGuardado.setIngredientes(ingredientesList);
        }

        if (platoDTO.getPreferencias() != null) {
            List<PreferenciasAlimenticiasDelPlato> preferenciasList = new ArrayList<>();
            for (Integer idPreferencia : platoDTO.getPreferencias()) {
                PreferenciaAlimenticia preferencia = preferenciaRepository.findById(idPreferencia)
                        .orElseThrow(() -> new RuntimeException("Preferencia no encontrada: " + idPreferencia));

                PreferenciasAlimenticiasDelPlato pap = new PreferenciasAlimenticiasDelPlato();
                pap.setPlato(platoGuardado);
                pap.setPreferenciaAlimenticia(preferencia);
                preferenciasList.add(pap);
            }
            platoGuardado.setPreferencias(preferenciasList);
        }

        return platoRepository.save(platoGuardado);
    }

    @Transactional
    public Plato actualizarPlato(Integer id, PlatoDTO platoDTO) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));

        plato.setNombre(platoDTO.getNombre());
        plato.setValor(platoDTO.getValor());
        plato.setDescripcion(platoDTO.getDescripcion());

        if (platoDTO.getIdNocheTematica() != null) {
            NocheTematica nocheTematica = nocheTematicaRepository.findById(platoDTO.getIdNocheTematica())
                    .orElseThrow(() -> new RuntimeException("Noche temática no encontrada"));
            plato.setNocheTematica(nocheTematica);
        }

        return platoRepository.save(plato);
    }

    @Transactional
    public void eliminarPlato(Integer id) {
        if (!platoRepository.existsById(id)) {
            throw new RuntimeException("Plato no encontrado");
        }
        platoRepository.deleteById(id);
    }

    public List<Plato> obtenerTodosLosPlatos() {
        return platoRepository.findAll();
    }

    public Plato obtenerPlato(Integer id) {
        return platoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
    }

    public List<TopPlatoDTO> obtenerTopPlatos(LocalDate desde, LocalDate hasta) {
        List<Object[]> resultados = platoRepository.findTop3PlatosMasPedidos(desde, hasta);
        List<TopPlatoDTO> topPlatos = new ArrayList<>();

        for (Object[] resultado : resultados) {
            String nombre = (String) resultado[0];
            Long vecesPedido = ((Number) resultado[1]).longValue();
            topPlatos.add(new TopPlatoDTO(nombre, vecesPedido));
        }

        return topPlatos;
    }
}
