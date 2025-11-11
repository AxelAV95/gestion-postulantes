package com.axelav95.gestion_postulantes.usecase.postulante;
import java.util.List;

import com.axelav95.gestion_postulantes.core.domain.Postulante;

public class ListarPostulantesUseCase {
    private final PostulanteRepository repository;

    public ListarPostulantesUseCase( PostulanteRepository repository ) {
        this.repository = repository;
    }

    public List< Postulante > ejecutar() {
        return repository.listar();
    }
}
