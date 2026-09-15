package com.api_eventos.APIEventos.repository;

import com.api_eventos.APIEventos.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    long countByEventoId(Long eventoId);

    boolean existsByEventoIdAndParticipanteId(Long eventoId, Long participanteId);

    Optional<Inscricao> findByEventoIdAndParticipanteId(
            Long eventoId,
            Long participanteId
    );

    List<Inscricao> findByEventoId(Long eventoId);
}
