package com.example.api.medicos.domain.consultas.validacoes.agendamento;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.ConsultasRepository;
import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultasRepository consultasRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = consultasRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Médico ja possui consulta agendada para esse hórario");
        }
    }
}
