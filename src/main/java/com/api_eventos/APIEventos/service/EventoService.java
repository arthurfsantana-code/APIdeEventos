package com.api_eventos.APIEventos.service;

import com.api_eventos.APIEventos.model.Evento;
import com.api_eventos.APIEventos.repository.EventoRepository;
import com.api_eventos.APIEventos.repository.InscricaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final InscricaoRepository inscricaoRepository;

    public EventoService(EventoRepository eventoRepository,
                         InscricaoRepository inscricaoRepository) {
        this.eventoRepository = eventoRepository;
        this.inscricaoRepository = inscricaoRepository;
    }

    public Evento cadastrar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Evento não encontrado"));
    }

    public long vagasRestantes(Long eventoId) {

        Evento evento = buscarPorId(eventoId);

        long inscricoes =
                inscricaoRepository.countByEventoId(eventoId);

        return evento.getCapacidadeMaxima() - inscricoes;
    }
}
