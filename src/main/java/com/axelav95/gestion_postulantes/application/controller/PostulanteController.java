package com.axelav95.gestion_postulantes.application.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.axelav95.gestion_postulantes.application.dto.PostulanteDTO;
import com.axelav95.gestion_postulantes.application.mapper.PostulanteMapper;
import com.axelav95.gestion_postulantes.core.domain.Postulante;
import com.axelav95.gestion_postulantes.usecase.postulante.BuscarPostulantePorIdUseCase;
import com.axelav95.gestion_postulantes.usecase.postulante.CrearPostulanteUseCase;
import com.axelav95.gestion_postulantes.usecase.postulante.EliminarPostulanteUseCase;
import com.axelav95.gestion_postulantes.usecase.postulante.ListarPostulantesUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {

    private final CrearPostulanteUseCase crearPostulanteUseCase;
    private final ListarPostulantesUseCase listarPostulantesUseCase;
    private final BuscarPostulantePorIdUseCase buscarPostulantePorIdUseCase;
    private final EliminarPostulanteUseCase eliminarPostulanteUseCase;
    private final PostulanteMapper mapper;

    public PostulanteController(CrearPostulanteUseCase crearPostulanteUseCase,
                                ListarPostulantesUseCase listarPostulantesUseCase,
                                BuscarPostulantePorIdUseCase buscarPostulantePorIdUseCase,
                                EliminarPostulanteUseCase eliminarPostulanteUseCase,
                                PostulanteMapper mapper) {
        this.crearPostulanteUseCase = crearPostulanteUseCase;
        this.listarPostulantesUseCase = listarPostulantesUseCase;
        this.buscarPostulantePorIdUseCase = buscarPostulantePorIdUseCase;
        this.eliminarPostulanteUseCase = eliminarPostulanteUseCase;
        this.mapper = mapper;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECLUTADOR')")
    @PostMapping
    public PostulanteDTO crearPostulante(@Valid @RequestBody PostulanteDTO dto) {
        Postulante postulante = mapper.toEntity(dto);
        Postulante creado = crearPostulanteUseCase.ejecutar(postulante);
        return mapper.toDto(creado);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<PostulanteDTO> listarPostulantes() {
        List<Postulante> postulantes = listarPostulantesUseCase.ejecutar();
        return postulantes.stream()
                .map(mapper::toDto)
                .toList();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public PostulanteDTO buscarPostulantePorId(@PathVariable Long id) {
        return buscarPostulantePorIdUseCase.ejecutar(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarPostulante(@PathVariable Long id) {
        eliminarPostulanteUseCase.ejecutar(id);
    }
}
