package com.axelav95.gestion_postulantes.infrastructure.config;

//esto sirve para configurar openapi
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestionPostulantesAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API gestión de postulantes")
                        .version("1.0")
                        .description("API para administrar postulantes, vacantes y postulaciones."));
    }
}