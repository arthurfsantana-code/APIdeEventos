package com.api_eventos.APIEventos.dto;

import java.time.LocalDateTime;

public record InscricaoResponse(
        Long id,
        Long eventoId,
        String eventoNome,
        Long participanteId,
        String participanteNome,
        String participanteEmail,
        LocalDateTime dataInscricao
) {
}