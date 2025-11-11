package com.axelav95.gestion_postulantes.application.controller;

import com.axelav95.gestion_postulantes.application.mapper.PostulanteMapper;
import com.axelav95.gestion_postulantes.core.domain.Postulante;
import com.axelav95.gestion_postulantes.infrastructure.security.JwtService;
import com.axelav95.gestion_postulantes.usecase.postulante.BuscarPostulantePorIdUseCase;
import com.axelav95.gestion_postulantes.usecase.postulante.CrearPostulanteUseCase;
import com.axelav95.gestion_postulantes.usecase.postulante.EliminarPostulanteUseCase;
import com.axelav95.gestion_postulantes.usecase.postulante.ListarPostulantesUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostulanteController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PostulanteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CrearPostulanteUseCase crearPostulanteUseCase;

    @MockitoBean
    private ListarPostulantesUseCase listarPostulantesUseCase;

    @MockitoBean
    private BuscarPostulantePorIdUseCase buscarPostulantePorIdUseCase;

    @MockitoBean
    private EliminarPostulanteUseCase eliminarPostulanteUseCase;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private PostulanteMapper mapper; // 👉 se mockea en lugar de importar

    @Test
    void debeCrearPostulanteConExito() throws Exception {
        Postulante p = new Postulante(1L, "Ana", "ana@mail.com", "1234", "3 años");

        Mockito.when(crearPostulanteUseCase.ejecutar(Mockito.any())).thenReturn(p);
        Mockito.when(mapper.toEntity(Mockito.any())).thenReturn(p);
        Mockito.when(mapper.toDto(Mockito.any())).thenReturn(
                new com.axelav95.gestion_postulantes.application.dto.PostulanteDTO(
                        1L, "Ana", "ana@mail.com", "1234", "3 años"
                )
        );

        mockMvc.perform(post("/api/postulantes")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "nombre": "Ana",
                                    "email": "ana@mail.com",
                                    "telefono": "1234",
                                    "experiencia": "3 años"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.email").value("ana@mail.com"));
    }
}
