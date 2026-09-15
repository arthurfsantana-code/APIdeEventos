package com.api_eventos.APIEventos.service;

import com.api_eventos.APIEventos.model.Evento;
import com.api_eventos.APIEventos.model.Inscricao;
import com.api_eventos.APIEventos.model.Participante;
import com.api_eventos.APIEventos.repository.EventoRepository;
import com.api_eventos.APIEventos.repository.InscricaoRepository;
import com.api_eventos.APIEventos.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;

    public InscricaoService(
            InscricaoRepository inscricaoRepository,
            EventoRepository eventoRepository,
            ParticipanteRepository participanteRepository) {

        this.inscricaoRepository = inscricaoRepository;
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
    }

    public Inscricao inscrever(Long eventoId, Long participanteId) {

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() ->
                        new RuntimeException("Evento não encontrado"));

        Participante participante = participanteRepository
                .findById(participanteId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Participante não encontrado"));

        if (inscricaoRepository
                .existsByEventoIdAndParticipanteId(
                        eventoId,
                        participanteId)) {

            throw new RuntimeException(
                    "Participante já está inscrito neste evento");
        }

        long quantidadeInscritos =
                inscricaoRepository.countByEventoId(eventoId);

        if (quantidadeInscritos >= evento.getCapacidadeMaxima()) {

            throw new RuntimeException(
                    "Evento está lotado");
        }

        Inscricao inscricao =
                new Inscricao(evento, participante);

        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> listarPorEvento(Long eventoId) {

        return inscricaoRepository.findByEventoId(eventoId);
    }
    public void cancelar(Long eventoId, Long participanteId) {

        Inscricao inscricao =
                inscricaoRepository
                        .findByEventoIdAndParticipanteId(
                                eventoId,
                                participanteId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inscrição não encontrada"));

        inscricaoRepository.delete(inscricao);
    }
}
