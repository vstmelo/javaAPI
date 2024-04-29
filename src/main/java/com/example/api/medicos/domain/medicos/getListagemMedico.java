package com.example.api.medicos.domain.medicos;

public record getListagemMedico(Long id, String nome, String crm, String email, Especialidade especialidade) {
    public getListagemMedico(Medicos medico){
        this(medico.getId(),medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getEspecialidade());
    }
}
