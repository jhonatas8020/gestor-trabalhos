package com.git.gestoracademico.mappers;

import com.git.gestoracademico.dtos.AlunoDto;
import com.git.gestoracademico.models.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoDto toDto(Aluno aluno);

    Aluno toDomain(AlunoDto dto);

}
