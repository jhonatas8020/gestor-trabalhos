package com.git.gestoracademico.services;

import com.git.gestoracademico.exceptions.GestorExceptionNotFound;
import com.git.gestoracademico.models.Aluno;
import com.git.gestoracademico.models.Orientador;
import com.git.gestoracademico.repositorys.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorRegistro(Long registro) {
        Optional<Aluno> aluno = alunoRepository.findById(registro);
        return aluno.orElseThrow(() -> new GestorExceptionNotFound("Aluno não encontrado"));
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long registro, Aluno alunoAtualizado) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new GestorExceptionNotFound("Aluno não encontrado") );

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setTurma(alunoAtualizado.getTurma());
        aluno.setCurso(alunoAtualizado.getCurso());
        aluno.setTelefone(alunoAtualizado.getTelefone());

        return alunoRepository.save(aluno);
    }

    public void deletar(Long registro) {
        Aluno aluno = alunoRepository.findById(registro)
                .orElseThrow(() -> new GestorExceptionNotFound("Aluno não encontrado"));
        alunoRepository.deleteById(aluno.getRegistroAluno());
    }
}
