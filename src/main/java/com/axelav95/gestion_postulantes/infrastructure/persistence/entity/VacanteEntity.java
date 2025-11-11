package com.axelav95.gestion_postulantes.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
public class VacanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String requisitos;

    public VacanteEntity() {}

    public VacanteEntity(Long id, String titulo, String descripcion, String requisitos) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getRequisitos() { return requisitos; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setRequisitos(String requisitos) { this.requisitos = requisitos; }
}
