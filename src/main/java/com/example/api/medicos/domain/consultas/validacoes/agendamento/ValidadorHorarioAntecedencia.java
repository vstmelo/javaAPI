package com.example.api.medicos.domain.consultas.validacoes.agendamento;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var dataAgora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }

    }
}
