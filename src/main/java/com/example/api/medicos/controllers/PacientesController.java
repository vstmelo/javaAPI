package com.example.api.medicos.controllers;

import com.example.api.medicos.domain.pacientes.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacientesController {
    @Autowired
    PacientesRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createPaciente(@RequestBody @Valid DataCreatePacientes paciente, UriComponentsBuilder uriBuilder){
        var novoPaciente = new Pacientes(paciente);
        repository.save(novoPaciente);
        var uri = uriBuilder.path("pacientes/{id}").buildAndExpand(novoPaciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new dadosPacientesAtualizados(novoPaciente));
    }
    @GetMapping
    public ResponseEntity<Page<getListagemPacientes>> listagemPacientes(@PageableDefault(size = 10, sort = {"nome"})Pageable pages){
        var page = repository.findAll(pages).map(getListagemPacientes::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity editPaciente(@RequestBody @Valid EditarCadastroPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.attCadastroPaciente(dados);
        return ResponseEntity.ok(new dadosPacientesAtualizados(paciente));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPaciente(@PathVariable Long id){
       repository.deleteById(id);
       return ResponseEntity.noContent().build();
    }
}
