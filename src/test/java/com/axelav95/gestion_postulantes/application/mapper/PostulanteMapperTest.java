package com.axelav95.gestion_postulantes.application.mapper;

import com.axelav95.gestion_postulantes.application.dto.PostulanteDTO;
import com.axelav95.gestion_postulantes.core.domain.Postulante;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PostulanteMapperTest {

    private final PostulanteMapper mapper = PostulanteMapper.INSTANCE;

    @Test
    void debeMapearCorrectamenteDeDtoAEntidad() {
        PostulanteDTO dto = new PostulanteDTO(null,"Ana", "ana@mail.com", "1234", "3 años");
        Postulante entidad = mapper.toEntity(dto);

        assertThat(entidad.getNombre()).isEqualTo("Ana");
        assertThat(entidad.getEmail()).isEqualTo("ana@mail.com");
    }

    @Test
    void debeMapearCorrectamenteDeEntidadADto() {
        Postulante entidad = new Postulante(1L, "Carlos", "carlos@mail.com", "5678", "5 años");
        PostulanteDTO dto = mapper.toDto(entidad);

        assertThat(dto.getNombre()).isEqualTo("Carlos");
        assertThat(dto.getEmail()).isEqualTo("carlos@mail.com");
    }
}
