package com.example.api.medicos.domain.consultas.validacoes;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.ConsultasRepository;
import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultasRepository consultasRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(6);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteComConsultaNoMesmoHorario= consultasRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if(pacienteComConsultaNoMesmoHorario){
            throw new ValidacaoException("Paciente já possui consulta agendada nesse dia");
        }


    }

}
