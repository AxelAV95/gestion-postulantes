package com.axelav95.gestion_postulantes.infrastructure.exception;

public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException( String mensaje ) {
        super( mensaje );
    }
    
}
