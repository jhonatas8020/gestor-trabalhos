package com.git.gestoracademico.mappers;

import com.git.gestoracademico.dtos.OrientadorDto;
import com.git.gestoracademico.models.Orientador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrientadorMapper {

    OrientadorDto toDto(Orientador orientador);

    Orientador toDomain(OrientadorDto dto);

}
