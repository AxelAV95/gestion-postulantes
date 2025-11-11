package com.axelav95.gestion_postulantes.application.mapper;

import com.axelav95.gestion_postulantes.core.domain.Postulante;
import com.axelav95.gestion_postulantes.application.dto.PostulanteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostulanteMapper {

    PostulanteMapper INSTANCE = Mappers.getMapper(PostulanteMapper.class);

    // Si los nombres coinciden, no necesitas @Mapping.
    Postulante toEntity(PostulanteDTO dto);

    PostulanteDTO toDto(Postulante entity);
}
