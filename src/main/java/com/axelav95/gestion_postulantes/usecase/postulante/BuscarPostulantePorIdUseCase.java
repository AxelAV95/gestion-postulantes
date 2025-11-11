package com.axelav95.gestion_postulantes.usecase.postulante;

import java.util.Optional;

import com.axelav95.gestion_postulantes.core.domain.Postulante;

public class BuscarPostulantePorIdUseCase {
    private final PostulanteRepository repository;

    public BuscarPostulantePorIdUseCase( PostulanteRepository repository ) {
        this.repository = repository;
    }

    public Optional< Postulante > ejecutar( Long id ) {
        return repository.obtenerPorId( id );
    }
}
