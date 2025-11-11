package com.axelav95.gestion_postulantes.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.axelav95.gestion_postulantes.core.domain.Postulante;
import com.axelav95.gestion_postulantes.core.repository.PostulanteRepository;
import com.axelav95.gestion_postulantes.infrastructure.persistence.entity.PostulanteEntity;
import com.axelav95.gestion_postulantes.infrastructure.persistence.jpa.PostulanteJpaRepository;

@Repository
public class PostulanteRepositoryImpl implements PostulanteRepository {

    private PostulanteJpaRepository jpaRepository;

    public PostulanteRepositoryImpl(PostulanteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Postulante guardar(Postulante postulante) {
        PostulanteEntity entity = new PostulanteEntity( postulante.getNombre(), postulante.getEmail(), postulante.getTelefono(), postulante.getExperiencia());
        PostulanteEntity guardado = jpaRepository.save(entity);
        postulante.setId(guardado.getId());
        
        return postulante;
    }

    @Override
    public List<Postulante> listar() {
        return jpaRepository.findAll().stream().map(entity -> new Postulante(
            entity.getId(),
            entity.getNombre(),
            entity.getEmail(),
            entity.getTelefono(),
            entity.getExperiencia()
        )).toList();
    }

    @Override
    public Optional<Postulante> obtenerPorId(Long id) {
        return jpaRepository.findById(id).map(entity -> new Postulante(
            entity.getId(),
            entity.getNombre(),
            entity.getEmail(),
            entity.getTelefono(),
            entity.getExperiencia()
        ));
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);        
    }
    
}
