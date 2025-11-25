package com.polichef.polichefApi.polichef.service;

import com.polichef.polichefApi.polichef.dto.NocheTematicaResponseDTO;
import com.polichef.polichefApi.polichef.dto.PlatoSimpleDTO;
import com.polichef.polichefApi.polichef.model.NocheTematica;
import com.polichef.polichefApi.polichef.model.Plato;
import com.polichef.polichefApi.polichef.repository.NocheTematicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NocheTematicaService {

    @Autowired
    private NocheTematicaRepository nocheTematicaRepository;

    public List<NocheTematicaResponseDTO> obtenerCalendarioTematicas() {
        LocalDate hoy = LocalDate.now();
        List<NocheTematica> noches = nocheTematicaRepository.findByFechaAfter(hoy);

        List<NocheTematicaResponseDTO> response = new ArrayList<>();
        for (NocheTematica noche : noches) {
            NocheTematicaResponseDTO dto = convertirADTO(noche);
            response.add(dto);
        }

        return response;
    }

    public List<NocheTematicaResponseDTO> obtenerTematicasPorRango(LocalDate inicio, LocalDate fin) {
        List<NocheTematica> noches = nocheTematicaRepository.findByFechaBetween(inicio, fin);

        List<NocheTematicaResponseDTO> response = new ArrayList<>();
        for (NocheTematica noche : noches) {
            NocheTematicaResponseDTO dto = convertirADTO(noche);
            response.add(dto);
        }

        return response;
    }

    public List<NocheTematica> obtenerTodasLasNoches() {
        return nocheTematicaRepository.findAll();
    }

    public Optional<NocheTematicaResponseDTO> obtenerTematicaSemanaActual() {
        LocalDate hoy = LocalDate.now();
        LocalDate sabado;

        DayOfWeek diaSemana = hoy.getDayOfWeek();

        if (diaSemana == DayOfWeek.SUNDAY) {
            sabado = hoy.minusDays(1);
        } else if (diaSemana == DayOfWeek.SATURDAY) {
            sabado = hoy;
        } else {
            sabado = hoy.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        }

        System.out.println("Fecha de hoy: " + hoy);
        System.out.println("Sábado calculado: " + sabado);

        Optional<NocheTematica> nocheOpt = nocheTematicaRepository.findByFecha(sabado);

        System.out.println("Temática encontrada: " + nocheOpt.isPresent());

        if (nocheOpt.isPresent()) {
            return Optional.of(convertirADTO(nocheOpt.get()));
        }

        return Optional.empty();
    }

    private NocheTematicaResponseDTO convertirADTO(NocheTematica noche) {
        NocheTematicaResponseDTO dto = new NocheTematicaResponseDTO();
        dto.setId(noche.getId());
        dto.setTitulo(noche.getTitulo());
        dto.setDescripcion(noche.getDescripcion());
        dto.setFecha(noche.getFecha());
        dto.setPais(noche.getPais().getNombre());

        List<PlatoSimpleDTO> platos = new ArrayList<>();
        if (noche.getPlatos() != null) {
            for (Plato plato : noche.getPlatos()) {
                PlatoSimpleDTO platoDTO = new PlatoSimpleDTO();
                platoDTO.setId(plato.getId());
                platoDTO.setNombre(plato.getNombre());
                platoDTO.setValor(plato.getValor());
                platoDTO.setDescripcion(plato.getDescripcion());
                platos.add(platoDTO);
            }
        }
        dto.setPlatos(platos);

        return dto;
    }
}