package com.api_eventos.APIEventos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ParticipanteRequest(

        @NotBlank(message = "O nome do participante é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail do participante é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email
) {
}