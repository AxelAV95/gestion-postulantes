package com.axelav95.gestion_postulantes.usecase.postulacion;

import java.util.Optional;

public class BuscarPostulacionPorIdUseCase {
    private final PostulacionRepository repository;

    public BuscarPostulacionPorIdUseCase( PostulacionRepository repository ) {
        this.repository = repository;
    }

    public Optional< Postulacion > ejecutar( Long id ) {
        return repository.obtenerPorId( id );
    }
}
