package com.example.api.medicos.domain.medicos;
import com.example.api.medicos.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record EditCadastroMedicos(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
