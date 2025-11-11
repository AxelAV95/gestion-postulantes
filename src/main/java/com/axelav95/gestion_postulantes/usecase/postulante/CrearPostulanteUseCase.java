package com.axelav95.gestion_postulantes.usecase.postulante;

import com.axelav95.gestion_postulantes.core.domain.Postulante;
import com.axelav95.gestion_postulantes.core.repository.PostulanteRepository;

public class CrearPostulanteUseCase {
    private final PostulanteRepository repository;

    //aqui se aplica el principio de inyeccion de dependencias
    public CrearPostulanteUseCase( PostulanteRepository repository ) {
        this.repository = repository;
    }

    public Postulante ejecutar( Postulante postulante ) {
        //lógica de negocio para crear un postulante
        return repository.guardar( postulante );
    }
}
