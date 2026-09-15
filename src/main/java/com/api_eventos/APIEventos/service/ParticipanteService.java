package com.api_eventos.APIEventos.service;

import com.api_eventos.APIEventos.model.Participante;
import com.api_eventos.APIEventos.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public ParticipanteService(
            ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public Participante cadastrar(Participante participante) {

        if (participanteRepository.existsByEmail(participante.getEmail())) {
            throw new RuntimeException(
                    "Já existe um participante com este e-mail"
            );
        }

        return participanteRepository.save(participante);
    }

    public List<Participante> listarTodos() {
        return participanteRepository.findAll();
    }

    public Participante buscarPorId(Long id) {
        return participanteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Participante não encontrado"
                        ));
    }
}
