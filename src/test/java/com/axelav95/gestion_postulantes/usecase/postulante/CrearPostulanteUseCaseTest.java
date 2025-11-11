package com.axelav95.gestion_postulantes.usecase.postulante;

import com.axelav95.gestion_postulantes.core.domain.Postulante;
import com.axelav95.gestion_postulantes.core.repository.PostulanteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrearPostulanteUseCaseTest {

    @Test
    void debeCrearUnPostulanteCorrectamente() {
        // Arrange
        PostulanteRepository repo = Mockito.mock(PostulanteRepository.class);
        CrearPostulanteUseCase useCase = new CrearPostulanteUseCase(repo);
        Postulante p = new Postulante(null, "Ana", "ana@mail.com", "1234", "3 años");

        Mockito.when(repo.guardar(p)).thenReturn(new Postulante(1L, "Ana", "ana@mail.com", "1234", "3 años"));

        // Act
        Postulante resultado = useCase.ejecutar(p);

        // Assert
        assertEquals(1L, resultado.getId());
        assertEquals("Ana", resultado.getNombre());
    }
}
