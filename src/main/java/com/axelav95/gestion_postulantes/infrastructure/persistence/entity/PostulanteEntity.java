package com.axelav95.gestion_postulantes.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "postulantes")
public class PostulanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;
    private String experiencia;

    public PostulanteEntity() {}

    public PostulanteEntity(Long id, String nombre, String email, String telefono, String experiencia) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.experiencia = experiencia;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getExperiencia() { return experiencia; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setExperiencia(String experiencia) { this.experiencia = experiencia; }
}