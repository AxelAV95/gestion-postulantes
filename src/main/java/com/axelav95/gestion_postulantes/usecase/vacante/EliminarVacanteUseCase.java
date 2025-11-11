package com.axelav95.gestion_postulantes.usecase.vacante;

import com.axelav95.gestion_postulantes.core.repository.VacanteRepository;

public class EliminarVacanteUseCase {
    private final VacanteRepository repository;

    public EliminarVacanteUseCase( VacanteRepository repository ) {
        this.repository = repository;
    }

    public void ejecutar( Long id ) {
        repository.eliminar( id );
    }
}
