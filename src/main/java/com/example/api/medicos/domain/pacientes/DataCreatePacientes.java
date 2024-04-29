package com.example.api.medicos.domain.pacientes;


import com.example.api.medicos.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCreatePacientes(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @Valid
        @NotNull
        DadosEndereco endereco
) {
}
