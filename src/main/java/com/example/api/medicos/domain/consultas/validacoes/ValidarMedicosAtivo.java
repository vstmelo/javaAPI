package com.example.api.medicos.domain.consultas.validacoes;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;
import com.example.api.medicos.domain.medicos.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicosAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private MedicosRepository medicosRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return;
        }
        var medicoAtivo = medicosRepository.findAtivoBy(dados.idMedico());
        if(!medicoAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada para este medico");
        }

    }
}
