package com.example.api.medicos.domain.consultas;

import com.example.api.medicos.domain.medicos.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull Long idPaciente,

        @NotNull @Future LocalDateTime data,

        Especialidade especialidade) {
}
