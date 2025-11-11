package com.axelav95.gestion_postulantes.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axelav95.gestion_postulantes.infrastructure.persistence.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}