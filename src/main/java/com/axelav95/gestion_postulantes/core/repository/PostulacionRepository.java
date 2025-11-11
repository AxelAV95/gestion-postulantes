package com.axelav95.gestion_postulantes.core.repository;

import java.util.List;
import java.util.Optional;

import com.axelav95.gestion_postulantes.core.domain.Postulacion;

public interface PostulacionRepository {
    Postulacion guardar( Postulacion postulacion );
    List< Postulacion > listar();
    Optional< Postulacion > obtenerPorId( Long id );
    void eliminar( Long id );
}
