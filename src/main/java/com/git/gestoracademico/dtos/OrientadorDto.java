package com.git.gestoracademico.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrientadorDto {

    private Long matricula;
    private String nome;
    private String telefone;
    private String areaConhecimento;
    private String titulacao;
    private List<String> disponibilidades;

}
