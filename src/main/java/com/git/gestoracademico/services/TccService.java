package com.git.gestoracademico.services;

import com.git.gestoracademico.dtos.AlunoDto;
import com.git.gestoracademico.dtos.TccDto;
import com.git.gestoracademico.exceptions.GestorExceptionNotFound;
import com.git.gestoracademico.mappers.AlunoMapper;
import com.git.gestoracademico.mappers.OrientadorMapper;
import com.git.gestoracademico.mappers.TccMapper;
import com.git.gestoracademico.models.Aluno;
import com.git.gestoracademico.models.Tcc;
import com.git.gestoracademico.repositorys.TccRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TccService {

    private final TccRepository tccRepository;

    private final TccMapper tccMapper;

    private final AlunoMapper alunoMapper;

    private final OrientadorMapper orientadorMapper;

    public List<TccDto> listarTodos() {
        List<Tcc> tccs = tccRepository.findAll();
        List<TccDto> tccDtos = new ArrayList<>();
        tccs.forEach(tcc -> tccDtos.add(tccMapper.toDto(tcc)));
        return tccDtos;
    }

    public TccDto buscarPorId(Long id) {
        Tcc tcc = tccRepository.findById(id)
                .orElseThrow(() -> new GestorExceptionNotFound("Tcc não encontrado"));
        return tccMapper.toDto(tcc);
    }

    public TccDto salvar(TccDto tccDto) {
        Tcc tcc = tccRepository.save(tccMapper.toDomain(tccDto));
        return tccMapper.toDto(tcc);
    }

    public TccDto atualizar(Long id, TccDto tccDto) {
        Tcc tcc = tccRepository.findById(id)
                .orElseThrow(() -> new GestorExceptionNotFound("Tcc não encontrado") );

        if(tccDto.getTitulo() != null) {
            tcc.setTitulo(tccDto.getTitulo());
        }

        if(tccDto.getIntegrantes() != null) {
            tcc.setIntegrantes(getListAluno(tccDto.getIntegrantes()));
        }

        if(tccDto.getOrientador() != null) {
            tcc.setOrientador(orientadorMapper.toDomain(tccDto.getOrientador()));
        }

        tccRepository.save(tcc);

        return tccMapper.toDto(tcc);
    }

    public void deletar(Long id) {
        Tcc tcc = tccRepository.findById(id)
                .orElseThrow(() -> new GestorExceptionNotFound("Tcc não encontrado"));
        tccRepository.deleteById(tcc.getId());
    }

    private List<Aluno> getListAluno(List<AlunoDto> alunosDtos) {
        List<Aluno> alunos = new ArrayList<>();
        alunosDtos.forEach(aluno -> alunos.add(alunoMapper.toDomain(aluno)));
        return alunos;
    }

}
