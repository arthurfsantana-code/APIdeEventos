package com.api_eventos.APIEventos.controller;

import com.api_eventos.APIEventos.dto.InscricaoResponse;
import com.api_eventos.APIEventos.model.Inscricao;
import com.api_eventos.APIEventos.service.InscricaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(
            InscricaoService inscricaoService) {

        this.inscricaoService = inscricaoService;
    }

    @PostMapping("/evento/{eventoId}/participante/{participanteId}")
    public ResponseEntity<InscricaoResponse> inscrever(
            @PathVariable Long eventoId,
            @PathVariable Long participanteId) {

        Inscricao inscricao =
                inscricaoService.inscrever(
                        eventoId,
                        participanteId
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(inscricao));
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<InscricaoResponse>> listarPorEvento(
            @PathVariable Long eventoId) {

        List<InscricaoResponse> inscricoes =
                inscricaoService.listarPorEvento(eventoId)
                        .stream()
                        .map(this::toResponse)
                        .toList();

        return ResponseEntity.ok(inscricoes);
    }

    @DeleteMapping(
            "/evento/{eventoId}/participante/{participanteId}"
    )
    public ResponseEntity<Void> cancelar(
            @PathVariable Long eventoId,
            @PathVariable Long participanteId) {

        inscricaoService.cancelar(
                eventoId,
                participanteId
        );

        return ResponseEntity.noContent().build();
    }

    private InscricaoResponse toResponse(Inscricao inscricao) {

        return new InscricaoResponse(
                inscricao.getId(),
                inscricao.getEvento().getId(),
                inscricao.getEvento().getNome(),
                inscricao.getParticipante().getId(),
                inscricao.getParticipante().getNome(),
                inscricao.getParticipante().getEmail(),
                inscricao.getDataInscricao()
        );
    }
}