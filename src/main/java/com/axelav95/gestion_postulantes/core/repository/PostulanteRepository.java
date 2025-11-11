package com.axelav95.gestion_postulantes.core.repository;

import java.util.List;
import java.util.Optional;

import com.axelav95.gestion_postulantes.core.domain.Postulante;

public interface PostulanteRepository {
    Postulante guardar( Postulante postulante );
    List< Postulante > listar();
    Optional< Postulante > obtenerPorId( Long id );
    void eliminar( Long id );

}
