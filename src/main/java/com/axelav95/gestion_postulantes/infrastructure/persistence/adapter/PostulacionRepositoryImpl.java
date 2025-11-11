package com.axelav95.gestion_postulantes.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.axelav95.gestion_postulantes.core.domain.Postulacion;
import com.axelav95.gestion_postulantes.core.repository.PostulacionRepository;
import com.axelav95.gestion_postulantes.infrastructure.persistence.entity.PostulacionEntity;
import com.axelav95.gestion_postulantes.infrastructure.persistence.jpa.PostulacionJpaRepository;

@Repository
public class PostulacionRepositoryImpl implements PostulacionRepository {

    private final PostulacionJpaRepository jpaRepository;
    public PostulacionRepositoryImpl(PostulacionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public Postulacion guardar(Postulacion postulacion) {
        PostulacionEntity entity = new PostulacionEntity(postulacion.getId(), postulacion.getPostulanteId(), postulacion.getVacanteId(), postulacion.getFechaPostulacion());
        PostulacionEntity guardado = jpaRepository.save(entity);
        postulacion.setId(guardado.getId());
        return postulacion;
    }

    @Override
    public List<Postulacion> listar() {
        return jpaRepository.findAll().stream().map(entity -> new Postulacion(
            entity.getId(),
            entity.getPostulanteId(),
            entity.getVacanteId(),
            entity.getFechaPostulacion()
        )).toList();
    }

    @Override
    public Optional<Postulacion> obtenerPorId(Long id) {
        return jpaRepository.findById(id).map(entity -> new Postulacion(
            entity.getId(),
            entity.getPostulanteId(),
            entity.getVacanteId(),
            entity.getFechaPostulacion()
        ));         
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);   
    }
    
}
