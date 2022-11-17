package com.git.gestoracademico.services;

import com.git.gestoracademico.dtos.AlunoDto;
import com.git.gestoracademico.exceptions.GestorExceptionNotFound;
import com.git.gestoracademico.mappers.AlunoMapper;
import com.git.gestoracademico.models.Aluno;
import com.git.gestoracademico.models.Orientador;
import com.git.gestoracademico.repositorys.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final AlunoMapper alunoMapper;

    public List<AlunoDto> listarTodos() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoDto> alunoDtos = new ArrayList<>();
        alunos.forEach(aluno -> alunoDtos.add(alunoMapper.toDto(aluno)));
        return alunoDtos;
    }

    public AlunoDto buscarPorRegistro(Long registro) {
        Optional<Aluno> aluno = alunoRepository.findById(registro);
        Optional<AlunoDto> alunoDto = Optional.ofNullable(alunoMapper.toDto(aluno.get()));
        return alunoDto.orElseThrow(() -> new GestorExceptionNotFound("Aluno não encontrado"));
    }

    public AlunoDto salvar(AlunoDto alunoDto) {
        Aluno aluno = alunoRepository.save(alunoMapper.toDomain(alunoDto));
        return alunoMapper.toDto(aluno);
    }

    public AlunoDto atualizar(Long registro, AlunoDto alunoAtualizado) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new GestorExceptionNotFound("Aluno não encontrado") );

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setTurma(alunoAtualizado.getTurma());
        aluno.setCurso(alunoAtualizado.getCurso());
        aluno.setTelefone(alunoAtualizado.getTelefone());
        alunoRepository.save(aluno);

        return alunoMapper.toDto(aluno);
    }

    public void deletar(Long registro) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new GestorExceptionNotFound("Aluno não encontrado"));
        alunoRepository.deleteById(aluno.getRegistroAluno());
    }
}
