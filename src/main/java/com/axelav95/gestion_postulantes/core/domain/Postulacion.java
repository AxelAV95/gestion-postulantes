package com.axelav95.gestion_postulantes.core.domain;

import java.time.LocalDate;

public class Postulacion {
    private Long id;
    private Long postulanteId;
    private Long vacanteId;
    private LocalDate fechaPostulacion;

    public Postulacion(Long id, Long postulanteId, Long vacanteId, LocalDate fechaPostulacion) {
        this.id = id;
        this.postulanteId = postulanteId;
        this.vacanteId = vacanteId;
        this.fechaPostulacion = fechaPostulacion;
    }

    public Postulacion(Long postulanteId, Long vacanteId, LocalDate fechaPostulacion) {
        this(null, postulanteId, vacanteId, fechaPostulacion);
    }

    public Postulacion() {}

    public Long getId() {
        return id;
    }

    public Long getPostulanteId() {
        return postulanteId;
    }

    public Long getVacanteId() {
        return vacanteId;
    }

    public LocalDate getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostulanteId(Long postulanteId) {
        this.postulanteId = postulanteId;
    }

    public void setVacanteId(Long vacanteId) {
        this.vacanteId = vacanteId;
    }

    public void setFechaPostulacion(LocalDate fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }
}
