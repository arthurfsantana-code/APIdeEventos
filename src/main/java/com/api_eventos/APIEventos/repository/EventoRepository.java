package com.api_eventos.APIEventos.repository;

import com.api_eventos.APIEventos.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
