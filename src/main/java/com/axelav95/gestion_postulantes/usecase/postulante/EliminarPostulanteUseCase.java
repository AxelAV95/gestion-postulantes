package com.axelav95.gestion_postulantes.usecase.postulante;

public class EliminarPostulanteUseCase {
    private final PostulanteRepository repository;

    public EliminarPostulanteUseCase( PostulanteRepository repository ) {
        this.repository = repository;
    }

    public void ejecutar( Long id ) {
        repository.eliminar( id );
    }
}
