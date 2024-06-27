package com.example.api.medicos.domain.consultas.validacoes;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class ValidarHorarioDeAgendamento {
    public void agendar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }

    }

    @Component
    public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{

        public void validar(DadosAgendamentoConsulta dados) {

            var dataConsulta = dados.data();
            var agora = LocalDateTime.now();
            var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

            if (diferencaEmMinutos < 30) {
                throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
            }
        }

    }
}
