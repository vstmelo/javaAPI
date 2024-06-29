package com.example.api.medicos.controllers;

import com.example.api.medicos.domain.consultas.AgendaDeConsultas;
import com.example.api.medicos.domain.consultas.ConsultasRepository;
import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;
import com.example.api.medicos.domain.consultas.DadosDetalhamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {
    @Autowired
    private AgendaDeConsultas agenda;
    @Autowired
    ConsultasRepository consultasRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

}
