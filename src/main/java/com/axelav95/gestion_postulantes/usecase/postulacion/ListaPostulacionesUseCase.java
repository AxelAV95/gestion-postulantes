package com.axelav95.gestion_postulantes.usecase.postulacion;

import java.util.List;

import com.axelav95.gestion_postulantes.core.domain.Postulacion;
import com.axelav95.gestion_postulantes.core.repository.PostulacionRepository;

public class ListaPostulacionesUseCase {
    private final PostulacionRepository repository;

    public ListaPostulacionesUseCase( PostulacionRepository repository ) {
        this.repository = repository;
    }

    public List< Postulacion > ejecutar() {
        return repository.listar();
    }
}
