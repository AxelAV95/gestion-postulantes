package com.axelav95.gestion_postulantes.usecase.vacante;
import java.util.List;

import com.axelav95.gestion_postulantes.core.domain.Vacante;
import com.axelav95.gestion_postulantes.core.repository.VacanteRepository;

public class ListarVacantesUseCase {
    private final VacanteRepository repository;

    public ListarVacantesUseCase( VacanteRepository repository ) {
        this.repository = repository;
    }

    public List< Vacante > ejecutar() {
        return repository.listar();
    }
}
