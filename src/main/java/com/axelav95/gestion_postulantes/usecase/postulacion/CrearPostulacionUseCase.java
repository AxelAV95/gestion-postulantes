package com.axelav95.gestion_postulantes.usecase.postulacion;

import com.axelav95.gestion_postulantes.core.domain.Postulacion;

public class CrearPostulacionUseCase {
    private final PostulacionRepository repository;

    public CrearPostulacionUseCase( PostulacionRepository repository ) {
        this.repository = repository;
    }

    public Postulacion ejecutar( Postulacion postulacion ) {
        return repository.guardar( postulacion );
    }
}
