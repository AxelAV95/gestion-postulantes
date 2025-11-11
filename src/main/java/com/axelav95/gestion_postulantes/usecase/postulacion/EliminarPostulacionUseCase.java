package com.axelav95.gestion_postulantes.usecase.postulacion;

public class EliminarPostulacionUseCase {
    private final PostulacionRepository repository;

    public EliminarPostulacionUseCase( PostulacionRepository repository ) {
        this.repository = repository;
    }
    public void ejecutar( Long id ) {
        repository.eliminar( id );
    }
}
