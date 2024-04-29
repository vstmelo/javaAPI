package com.example.api.medicos.controllers;

import com.example.api.medicos.domain.medicos.*;
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
@RequestMapping("medicos")
public class MedicosController {
    @Autowired
    MedicosRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedicos(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder){
        var novoPaciente = new Medicos(dados);
        repository.save(novoPaciente);
        var uri = uriComponentsBuilder.path("medicos/{id}").buildAndExpand(novoPaciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosMedicoAtualizado(novoPaciente));
    }
    @GetMapping
    public ResponseEntity<Page<getListagemMedico>> listMedicos(@PageableDefault(size = 10, sort = {"nome"})Pageable pages){
        var page = repository.findAllByAtivoTrue(pages).map(getListagemMedico::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity editMedicos(@RequestBody @Valid EditCadastroMedicos dados){
        var medico = repository.getReferenceById(dados.id());
        medico.attCadastro(dados);
        return ResponseEntity.ok(new DadosMedicoAtualizado(medico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity setInative(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.setInative(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosMedicoAtualizado(medico));
    }
}
