package com.api_eventos.APIEventos.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventoRequest(

        @NotBlank(message = "O nome do evento é obrigatório")
        String nome,

        @NotBlank(message = "A descrição do evento é obrigatória")
        String descricao,

        @NotNull(message = "A data do evento é obrigatória")
        @Future(message = "A data do evento deve ser futura")
        LocalDateTime data,

        @NotBlank(message = "O local do evento é obrigatório")
        String local,

        @NotNull(message = "A capacidade máxima é obrigatória")
        @Min(value = 1, message = "A capacidade máxima deve ser maior que zero")
        Integer capacidadeMaxima
) {
}