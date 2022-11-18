package com.git.gestoracademico.mappers;

import com.git.gestoracademico.dtos.TccDto;
import com.git.gestoracademico.models.Tcc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TccMapper {


    TccDto toDto(Tcc tcc);


    Tcc toDomain(TccDto dto);

}
