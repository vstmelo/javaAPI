package com.example.api.medicos.domain.consultas.validacoes.cancelamento;

import com.example.api.medicos.domain.consultas.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
