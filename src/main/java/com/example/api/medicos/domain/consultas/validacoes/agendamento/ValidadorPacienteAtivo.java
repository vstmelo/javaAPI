package com.example.api.medicos.domain.consultas.validacoes.agendamento;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.DadosAgendamentoConsulta;
import com.example.api.medicos.domain.pacientes.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private PacientesRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo =  repository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído!");
        }
    }
}
