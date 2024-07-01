package com.example.api.medicos.domain.consultas.validacoes.agendamento;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioDeAgendamento implements ValidadorAgendamentoDeConsulta{
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }

    }
}
