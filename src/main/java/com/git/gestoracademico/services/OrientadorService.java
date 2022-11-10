package com.git.gestoracademico.services;

import com.git.gestoracademico.exceptions.GestorExceptionNotFound;
import com.git.gestoracademico.models.Orientador;
import com.git.gestoracademico.repositorys.OrientadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrientadorService {

    private final OrientadorRepository orientadorRepository;

    public List<Orientador> listarTodos() {
        return orientadorRepository.findAll();
    }

    public Orientador buscarPorMatricula(Long matricula) {
        Optional<Orientador> orientador = orientadorRepository.findById(matricula);
        return orientador.orElseThrow(() -> new GestorExceptionNotFound("Orientador não encontrado"));
    }

    public Orientador salvar(Orientador orientador) {
        return orientadorRepository.save(orientador);
    }

    public Orientador atualizar(Long matricula, Orientador orientadorAtualizado) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new GestorExceptionNotFound("Orientador não encontrado") );

        orientador.setNome(orientadorAtualizado.getNome());
        orientador.setTitulacao(orientadorAtualizado.getTitulacao());
        orientador.setTelefone(orientadorAtualizado.getTelefone());

        return orientadorRepository.save(orientador);
    }

    public void deletar(Long matricula) {
        Orientador orientador = orientadorRepository.findById(matricula)
                .orElseThrow(() -> new GestorExceptionNotFound("Orientador não encontrado"));
        orientadorRepository.deleteById(orientador.getMatricula());
    }
}
