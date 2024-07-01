package com.example.api.medicos.domain.consultas.validacoes.agendamento;

import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
