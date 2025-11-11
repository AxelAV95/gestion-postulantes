package com.axelav95.gestion_postulantes.application.dto;

public class PostulanteDTO {
        private Long id;
        private String nombre;
        private String email;
        private String telefono;
        private String experiencia;

        public PostulanteDTO() {
        }
        public PostulanteDTO(Long id, String nombre, String email, String telefono, String experiencia) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.telefono = telefono;
            this.experiencia = experiencia;
        }

        // Getters y Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public String getTelefono() {
            return telefono;
        }
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
        public String getExperiencia() {
            return experiencia;
        }
        public void setExperiencia(String experiencia) {
            this.experiencia = experiencia;
        }
}
