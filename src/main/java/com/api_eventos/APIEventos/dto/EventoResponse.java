package com.api_eventos.APIEventos.dto;

import java.time.LocalDateTime;

public record EventoResponse(
        Long id,
        String nome,
        String descricao,
        LocalDateTime data,
        String local,
        Integer capacidadeMaxima,
        long vagasRestantes
) {
}