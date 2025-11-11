package com.axelav95.gestion_postulantes.usecase.postulante;

import com.axelav95.gestion_postulantes.core.repository.PostulanteRepository;

public class EliminarPostulanteUseCase {
    private final PostulanteRepository repository;

    public EliminarPostulanteUseCase( PostulanteRepository repository ) {
        this.repository = repository;
    }

    public void ejecutar( Long id ) {
        repository.eliminar( id );
    }
}
