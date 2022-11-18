package com.git.gestoracademico.services;

import com.git.gestoracademico.dtos.OrientadorDto;
import com.git.gestoracademico.exceptions.GestorExceptionNotFound;
import com.git.gestoracademico.mappers.OrientadorMapper;
import com.git.gestoracademico.models.Orientador;
import com.git.gestoracademico.models.enums.Disponibilidades;
import com.git.gestoracademico.models.enums.Titulacao;
import com.git.gestoracademico.repositorys.OrientadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrientadorService {

    private final OrientadorRepository orientadorRepository;

    private final OrientadorMapper orientadorMapper;

    public List<OrientadorDto> listarTodos() {
        List<Orientador> orientadores = orientadorRepository.findAll();
        List<OrientadorDto> orientadoresDtos = new ArrayList<>();
        orientadores.forEach(orientador -> orientadoresDtos.add(orientadorMapper.toDto(orientador)));
        return orientadoresDtos;
    }

    public OrientadorDto buscarPorMatricula(Long matricula) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new GestorExceptionNotFound("Orientador não encontrado"));
        return orientadorMapper.toDto(orientador);
    }

    public OrientadorDto salvar(OrientadorDto orientadorDto) {
        Orientador orientador = orientadorRepository.save(orientadorMapper.toDomain(orientadorDto));
        return orientadorMapper.toDto(orientador);
    }

    public OrientadorDto atualizar(Long matricula, OrientadorDto orientadorDto) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new GestorExceptionNotFound("Orientador não encontrado") );

        if(orientadorDto.getNome() != null) {
            orientador.setNome(orientadorDto.getNome());
        }

        if(orientadorDto.getTelefone() != null) {
            orientador.setTelefone(orientadorDto.getTelefone());
        }

        if(orientadorDto.getAreaConhecimento() != null) {
            orientador.setAreaConhecimento(orientadorDto.getAreaConhecimento());
        }

        if(orientadorDto.getTitulacao() != null) {
            orientador.setTitulacao(Enum.valueOf(Titulacao.class, orientadorDto.getTitulacao()));
        }

        if(orientadorDto.getDisponibilidades() != null) {
            orientador.setDisponibilidades(getListDisponibilidades(orientadorDto.getDisponibilidades()));
        }

        orientadorRepository.save(orientador);

        return orientadorMapper.toDto(orientador);
    }

    public void deletar(Long matricula) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new GestorExceptionNotFound("Orientador não encontrado"));
        orientadorRepository.deleteById(orientador.getMatricula());
    }

    private List<Disponibilidades> getListDisponibilidades(List<String> disponibilidades) {
        List<Disponibilidades> list = new ArrayList<>();
        disponibilidades.forEach(disponibilidade ->
                list.add(Enum.valueOf(Disponibilidades.class, disponibilidade)));
        return list;
    }
}
