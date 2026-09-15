package com.api_eventos.APIEventos.controller;

import com.api_eventos.APIEventos.dto.ParticipanteRequest;
import com.api_eventos.APIEventos.dto.ParticipanteResponse;
import com.api_eventos.APIEventos.model.Participante;
import com.api_eventos.APIEventos.service.ParticipanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    private final ParticipanteService participanteService;

    public ParticipanteController(
            ParticipanteService participanteService) {

        this.participanteService = participanteService;
    }

    // RF04 - Cadastrar participante
    @PostMapping
    public ResponseEntity<ParticipanteResponse> cadastrar(
            @Valid @RequestBody ParticipanteRequest request) {

        Participante participante = new Participante(
                request.nome(),
                request.email()
        );

        Participante participanteSalvo =
                participanteService.cadastrar(participante);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(participanteSalvo));
    }

    // Listar participantes
    @GetMapping
    public ResponseEntity<List<ParticipanteResponse>> listarTodos() {

        List<ParticipanteResponse> participantes =
                participanteService.listarTodos()
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(participantes);
    }

    // Buscar participante por ID
    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteResponse> buscarPorId(
            @PathVariable Long id) {

        Participante participante =
                participanteService.buscarPorId(id);

        return ResponseEntity.ok(toResponse(participante));
    }

    private ParticipanteResponse toResponse(
            Participante participante) {

        return new ParticipanteResponse(
                participante.getId(),
                participante.getNome(),
                participante.getEmail()
        );
    }
}