package com.axelav95.gestion_postulantes.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.axelav95.gestion_postulantes.core.repository.PostulanteRepository;
import com.axelav95.gestion_postulantes.usecase.postulante.*;

@Configuration
public class UseCaseConfig {

    @Bean
    public CrearPostulanteUseCase crearPostulanteUseCase(PostulanteRepository repository) {
        return new CrearPostulanteUseCase(repository);
    }

    @Bean
    public ListarPostulantesUseCase listarPostulantesUseCase(PostulanteRepository repository) {
        return new ListarPostulantesUseCase(repository);
    }

    @Bean
    public BuscarPostulantePorIdUseCase buscarPostulantePorIdUseCase(PostulanteRepository repository) {
        return new BuscarPostulantePorIdUseCase(repository);
    }

    @Bean
    public EliminarPostulanteUseCase eliminarPostulanteUseCase(PostulanteRepository repository) {
        return new EliminarPostulanteUseCase(repository);
    }
}
