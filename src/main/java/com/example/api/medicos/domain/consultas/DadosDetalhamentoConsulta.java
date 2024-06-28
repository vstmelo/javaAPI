package com.example.api.medicos.domain.consultas;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long id,
        Long idMedico,
        Long idPaciente, LocalDateTime data
) {
    public DadosDetalhamentoConsulta(Consultas consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
