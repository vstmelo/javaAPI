package com.example.api.medicos.domain.consultas.validacoes.cancelamento;

import com.example.api.medicos.domain.ValidacaoException;
import com.example.api.medicos.domain.consultas.ConsultasRepository;
import com.example.api.medicos.domain.consultas.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoConsulta{
    @Autowired
    private ConsultasRepository repository;

    @Override
    public void validar(DadosCancelamentoConsulta dados){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var horaAtual = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(horaAtual, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }

}
