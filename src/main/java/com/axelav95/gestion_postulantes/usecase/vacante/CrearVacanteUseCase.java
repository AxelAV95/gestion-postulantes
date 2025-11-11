package com.axelav95.gestion_postulantes.usecase.vacante;

import com.axelav95.gestion_postulantes.core.domain.Vacante;

public class CrearVacanteUseCase {
   private final VacanteRepository repository;
    public CrearVacanteUseCase( VacanteRepository repository ) {
         this.repository = repository;
    }


    public Vacante ejecutar( Vacante vacante ) {
        //lógica de negocio para crear una vacante
        return repository.guardar( vacante );
    }
}
