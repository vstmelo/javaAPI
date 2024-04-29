package com.example.api.medicos.domain.pacientes;
import com.example.api.medicos.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record  EditarCadastroPaciente(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEndereco endereco
) {
}
