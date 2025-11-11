package com.axelav95.gestion_postulantes.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.axelav95.gestion_postulantes.core.domain.Vacante;
import com.axelav95.gestion_postulantes.core.repository.VacanteRepository;
import com.axelav95.gestion_postulantes.infrastructure.persistence.entity.VacanteEntity;
import com.axelav95.gestion_postulantes.infrastructure.persistence.jpa.VacanteJpaRepository;

@Repository
public class VacanteRepositoryImpl implements VacanteRepository {

    private final VacanteJpaRepository jpaRepository;

    public VacanteRepositoryImpl( VacanteJpaRepository jpaRepository ) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public Vacante guardar(Vacante vacante) {
        VacanteEntity entity = new VacanteEntity(vacante.getId(), vacante.getTitulo(), vacante.getDescripcion(), vacante.getRequisitos());
        VacanteEntity guardado = jpaRepository.save(entity);
        vacante.setId(guardado.getId());
        return vacante;
    }

    @Override
    public List<Vacante> listar() {
        return jpaRepository.findAll().stream().map(entity -> new Vacante(
            entity.getId(),
            entity.getTitulo(),
            entity.getDescripcion(),
            entity.getRequisitos()
        )).toList();
        
    }

    @Override
    public Optional<Vacante> obtenerPorId(Long id) {
        return jpaRepository.findById(id).map(entity -> new Vacante(
            entity.getId(),
            entity.getTitulo(),
            entity.getDescripcion(),
            entity.getRequisitos()
        ));
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }   
    
    
}
