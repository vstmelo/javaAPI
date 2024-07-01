package com.example.api.medicos.domain.consultas;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import com.example.api.medicos.domain.consultas.validacoes.cancelamento.ValidadorCancelamentoConsulta;
import com.example.api.medicos.domain.medicos.Medicos;
import com.example.api.medicos.domain.medicos.MedicosRepository;
import com.example.api.medicos.domain.pacientes.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    ConsultasRepository consultasRepository;
    @Autowired
    private MedicosRepository medicosRepository;
    @Autowired
    private PacientesRepository pacientesRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validadorCancelamentoConsultas;
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacientesRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Paciente não encontrado! ");
        }
        if (dados.idMedico() != null && !medicosRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }
        validadores.forEach(v->v.validar(dados));

        var paciente = pacientesRepository.getReferenceById(dados.idPaciente());

        var medico = escolherMedico(dados);

        if(medico ==null){
            throw new ValidacaoException("Não existe médico disponível nessa data!");

        }
        var consulta = new Consultas(null, medico, paciente, dados.data(), null);
        consultasRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medicos escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() !=null){
            return medicosRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade obrigatória quando medico não for escolhido! ");
        }

        return medicosRepository.escolherMedicoDisponivel(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados){
        if(!consultasRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe!");
        }
        validadorCancelamentoConsultas.forEach(v -> v.validar(dados));
        var consulta = consultasRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivoCancelamento());
    }
}
