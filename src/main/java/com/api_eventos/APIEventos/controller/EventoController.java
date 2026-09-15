package com.api_eventos.APIEventos.controller;

import com.api_eventos.APIEventos.dto.EventoRequest;
import com.api_eventos.APIEventos.dto.EventoResponse;
import com.api_eventos.APIEventos.model.Evento;
import com.api_eventos.APIEventos.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // RF01 - Cadastrar evento
    @PostMapping
    public ResponseEntity<EventoResponse> cadastrar(
            @Valid @RequestBody EventoRequest request) {

        Evento evento = new Evento(
                request.nome(),
                request.descricao(),
                request.data(),
                request.local(),
                request.capacidadeMaxima()
        );

        Evento eventoSalvo = eventoService.cadastrar(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(eventoSalvo));
    }

    // RF02 - Listar todos os eventos
    @GetMapping
    public ResponseEntity<List<EventoResponse>> listarTodos() {

        List<EventoResponse> eventos = eventoService.listarTodos()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(eventos);
    }

    // RF03 - Consultar evento e vagas restantes
    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarPorId(
            @PathVariable Long id) {

        Evento evento = eventoService.buscarPorId(id);

        return ResponseEntity.ok(toResponse(evento));
    }

    private EventoResponse toResponse(Evento evento) {

        long vagasRestantes =
                eventoService.vagasRestantes(evento.getId());

        return new EventoResponse(
                evento.getId(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getData(),
                evento.getLocal(),
                evento.getCapacidadeMaxima(),
                vagasRestantes
        );
    }
}