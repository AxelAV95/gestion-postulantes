package com.axelav95.gestion_postulantes.core.domain;

public class Vacante {

    private Long id;
    private String titulo;
    private String descripcion;
    private String requisitos;

    public Vacante(Long id, String titulo, String descripcion, String requisitos) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
    }

    public Vacante(String titulo, String descripcion, String requisitos) {
        this(null, titulo, descripcion, requisitos);
    }

    public Vacante() {}

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getRequisitos() { return requisitos; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setRequisitos(String requisitos) { this.requisitos = requisitos; }
}
