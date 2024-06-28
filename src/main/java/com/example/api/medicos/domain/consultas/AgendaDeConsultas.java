package com.example.api.medicos.domain.consultas;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.validacoes.ValidadorAgendamentoDeConsulta;
import com.example.api.medicos.domain.medicos.Medicos;
import com.example.api.medicos.domain.medicos.MedicosRepository;
import com.example.api.medicos.domain.pacientes.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        var consulta = new Consultas(null, medico, paciente, dados.data());
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
}
