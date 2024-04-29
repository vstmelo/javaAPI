package com.example.api.medicos.domain.pacientes;

import com.example.api.medicos.domain.endereco.Endereco;

public record getListagemPacientes(
        Long id,
        String nome,
        String email,
        Endereco endereco
) {
    public getListagemPacientes(Pacientes paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getEndereco());
    }

}
