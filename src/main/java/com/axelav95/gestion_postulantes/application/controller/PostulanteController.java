package com.axelav95.gestion_postulantes.application.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axelav95.gestion_postulantes.application.dto.PostulanteDTO;
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

    public PostulanteController( CrearPostulanteUseCase crearPostulanteUseCase,
                                 ListarPostulantesUseCase listarPostulantesUseCase,
                                 BuscarPostulantePorIdUseCase buscarPostulantePorIdUseCase,
                                 EliminarPostulanteUseCase eliminarPostulanteUseCase ) {
        this.crearPostulanteUseCase = crearPostulanteUseCase;
        this.listarPostulantesUseCase = listarPostulantesUseCase;
        this.buscarPostulantePorIdUseCase = buscarPostulantePorIdUseCase;
        this.eliminarPostulanteUseCase = eliminarPostulanteUseCase;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECLUTADOR')")
    @PostMapping
    public PostulanteDTO crearPostulante(@Valid @RequestBody PostulanteDTO dto) {
        Postulante postulante = new Postulante(dto.getNombre(), dto.getEmail(), dto.getTelefono(), dto.getExperiencia());
        Postulante creado = crearPostulanteUseCase.ejecutar(postulante);
        return new PostulanteDTO(creado.getId(), creado.getNombre(), creado.getEmail(), creado.getTelefono(), creado.getExperiencia())
        ;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<PostulanteDTO> listarPostulantes() {
        List<Postulante> postulantes = listarPostulantesUseCase.ejecutar();
        return postulantes.stream()
                .map(p -> new PostulanteDTO(p.getId(), p.getNombre(), p.getEmail(), p.getTelefono(), p.getExperiencia()))
                .toList();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public PostulanteDTO buscarPostulantePorId(@PathVariable Long id) {
        return buscarPostulantePorIdUseCase.ejecutar(id)
                .map(p -> new PostulanteDTO(p.getId(), p.getNombre(), p.getEmail(), p.getTelefono(), p.getExperiencia()))
                .orElse(null);
    }

     @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarPostulante(@PathVariable Long id) {
        eliminarPostulanteUseCase.ejecutar(id);
    }
}
