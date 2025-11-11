package com.axelav95.gestion_postulantes.usecase.postulante;

import java.util.Optional;

import com.axelav95.gestion_postulantes.core.domain.Postulante;
import com.axelav95.gestion_postulantes.core.repository.PostulanteRepository;
import com.axelav95.gestion_postulantes.infrastructure.exception.RecursoNoEncontradoException;

public class BuscarPostulantePorIdUseCase {
    private final PostulanteRepository repository;

    public BuscarPostulantePorIdUseCase( PostulanteRepository repository ) {
        this.repository = repository;
    }

    public Optional< Postulante > ejecutar( Long id ) {
        Optional<Postulante> postulante = repository.obtenerPorId( id );
        if (postulante.isEmpty()) {
            throw new RecursoNoEncontradoException( "Postulante no encontrado " + id);
        }
        return postulante;
    }
}
