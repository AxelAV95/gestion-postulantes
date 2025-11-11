package com.axelav95.gestion_postulantes.core.repository;

import java.util.List;
import java.util.Optional;

import com.axelav95.gestion_postulantes.core.domain.Vacante;

public interface VacanteRepository {
    Vacante guardar( Vacante vacante );
    List< Vacante > listar();
    Optional< Vacante > obtenerPorId( Long id );
    void eliminar( Long id );
}
