package com.axelav95.gestion_postulantes.usecase.postulacion;

import com.axelav95.gestion_postulantes.core.repository.PostulacionRepository;

public class EliminarPostulacionUseCase {
    private final PostulacionRepository repository;

    public EliminarPostulacionUseCase( PostulacionRepository repository ) {
        this.repository = repository;
    }
    public void ejecutar( Long id ) {
        repository.eliminar( id );
    }
}
