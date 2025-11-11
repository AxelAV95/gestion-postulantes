package com.axelav95.gestion_postulantes.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axelav95.gestion_postulantes.infrastructure.persistence.entity.PostulanteEntity;

public interface PostulanteJpaRepository extends JpaRepository<PostulanteEntity, Long> {}