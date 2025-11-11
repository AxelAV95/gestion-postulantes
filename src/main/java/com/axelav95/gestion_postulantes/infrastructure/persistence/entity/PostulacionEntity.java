package com.axelav95.gestion_postulantes.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "postulaciones")
public class PostulacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postulanteId;
    private Long vacanteId;
    private LocalDate fechaPostulacion;

    public PostulacionEntity() {}

    public PostulacionEntity(Long id, Long postulanteId, Long vacanteId, LocalDate fechaPostulacion) {
        this.id = id;
        this.postulanteId = postulanteId;
        this.vacanteId = vacanteId;
        this.fechaPostulacion = fechaPostulacion;
    }

    public Long getId() { return id; }
    public Long getPostulanteId() { return postulanteId; }
    public Long getVacanteId() { return vacanteId; }
    public LocalDate getFechaPostulacion() { return fechaPostulacion; }

    public void setId(Long id) { this.id = id; }
    public void setPostulanteId(Long postulanteId) { this.postulanteId = postulanteId; }
    public void setVacanteId(Long vacanteId) { this.vacanteId = vacanteId; }
    public void setFechaPostulacion(LocalDate fechaPostulacion) { this.fechaPostulacion = fechaPostulacion; }
}