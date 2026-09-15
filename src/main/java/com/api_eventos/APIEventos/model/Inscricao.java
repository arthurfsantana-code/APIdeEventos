package com.api_eventos.APIEventos.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "inscricoes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"evento_id", "participante_id"})
        }
)
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "participante_id")
    private Participante participante;

    private LocalDateTime dataInscricao;

    public Inscricao() {
    }

    public Inscricao(Evento evento, Participante participante) {
        this.evento = evento;
        this.participante = participante;
        this.dataInscricao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDateTime dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
}
