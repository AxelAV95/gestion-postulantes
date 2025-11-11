package com.axelav95.gestion_postulantes.usecase.vacante;

import com.axelav95.gestion_postulantes.core.domain.Vacante;

public class BuscarVacantePorIdUseCase {
    private final VacanteRepository repository;

    public BuscarVacantePorIdUseCase( VacanteRepository repository ) {
        this.repository = repository;
    }

    public Vacante ejecutar( Long id ) {
        return repository.obtenerPorId( id )
                .orElseThrow( () -> new RuntimeException( "Vacante no encontrada con id: " + id ) );
    }
}
