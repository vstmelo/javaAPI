package com.example.api.medicos.domain.consultas.validacoes;

import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
