package com.axelav95.gestion_postulantes.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axelav95.gestion_postulantes.infrastructure.persistence.entity.VacanteEntity;

public interface VacanteJpaRepository extends JpaRepository<VacanteEntity, Long> {
}
