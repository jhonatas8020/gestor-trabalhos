package com.git.gestoracademico.models;

import com.git.gestoracademico.models.enums.Disponibilidades;
import com.git.gestoracademico.models.enums.Titulacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_orientador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orientador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String nome;
    private String telefone;
    private String areaConhecimento;

    @Enumerated(EnumType.STRING)
    private Titulacao titulacao;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Disponibilidades> disponibilidades;

}
