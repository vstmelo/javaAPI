package com.example.api.medicos.domain.medicos;

import com.example.api.medicos.domain.endereco.Endereco;

public record DadosMedicoAtualizado(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosMedicoAtualizado(Medicos medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
