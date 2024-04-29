package com.example.api.medicos.domain.pacientes;

import com.example.api.medicos.domain.endereco.Endereco;

public record dadosPacientesAtualizados(Long id, String nome, String email, String telefone, Endereco endereco) {
    public dadosPacientesAtualizados(Pacientes paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
    }
}
