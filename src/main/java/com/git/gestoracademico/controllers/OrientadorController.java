package com.git.gestoracademico.controllers;

import com.git.gestoracademico.dtos.OrientadorDto;
import com.git.gestoracademico.models.Orientador;
import com.git.gestoracademico.services.OrientadorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/orientadores")
@AllArgsConstructor
public class OrientadorController {

    private final OrientadorService orientadorService;

    @GetMapping
    public ResponseEntity<List<OrientadorDto>> getAll() {
        return ResponseEntity.ok(orientadorService.listarTodos());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<OrientadorDto> procurarPorMatricula(@PathVariable Long matricula) {
        return new ResponseEntity<>(orientadorService.buscarPorMatricula(matricula), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrientadorDto> save(@RequestBody OrientadorDto orientador) {
        return new ResponseEntity<>(orientadorService.salvar(orientador), HttpStatus.CREATED);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<OrientadorDto> atualizar(@PathVariable Long matricula,
                                                @RequestBody OrientadorDto orientador) {

        return new ResponseEntity<>(orientadorService.atualizar(matricula, orientador), HttpStatus.OK);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable Long matricula) {
        orientadorService.deletar(matricula);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
