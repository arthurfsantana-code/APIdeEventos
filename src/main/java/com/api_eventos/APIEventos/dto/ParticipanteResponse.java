package com.api_eventos.APIEventos.dto;

public record ParticipanteResponse(
        Long id,
        String nome,
        String email
) {
}