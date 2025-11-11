package com.axelav95.gestion_postulantes.application.dto;

public class PostulacionDTO {
    private Long id;
    private Long postulanteId;
    private Long vacanteId;
    private String fechaPostulacion;

    public PostulacionDTO() {}

    public PostulacionDTO(Long id, Long postulanteId, Long vacanteId, String fechaPostulacion) {
        this.id = id;
        this.postulanteId = postulanteId;
        this.vacanteId = vacanteId;
        this.fechaPostulacion = fechaPostulacion;
    }

    public Long getId() { return id; }
    public Long getPostulanteId() { return postulanteId; }
    public Long getVacanteId() { return vacanteId; }
    public String getFechaPostulacion() { return fechaPostulacion; }
    public void setId(Long id) { this.id = id; }
    public void setPostulanteId(Long postulanteId) { this.postulanteId = postulanteId; }
    public void setVacanteId(Long vacanteId) { this.vacanteId = vacanteId; }
    public void setFechaPostulacion(String fechaPostulacion) { this.fechaPostulacion = fechaPostulacion; }
}
