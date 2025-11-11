package com.axelav95.gestion_postulantes.usecase.vacante;

public class EliminarVacanteUseCase {
    private final VacanteRepository repository;

    public EliminarVacanteUseCase( VacanteRepository repository ) {
        this.repository = repository;
    }

    public void ejecutar( Long id ) {
        repository.eliminar( id );
    }
}
