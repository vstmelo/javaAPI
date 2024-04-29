package com.example.api.medicos.domain.pacientes;

import com.example.api.medicos.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="pacientes")
@Entity(name="Pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pacientes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;

    public Pacientes(DataCreatePacientes paciente) {
        this.nome=paciente.nome();
        this.email=paciente.email();
        this.telefone=paciente.telefone();
        this.endereco= new Endereco(paciente.endereco());
    }

    public void attCadastroPaciente(EditarCadastroPaciente dados) {
        if(dados.nome() != null){

            this.nome=dados.nome();
        }
        if(dados.telefone() != null){

            this.telefone=dados.telefone();
        }
        if(dados.endereco() != null){

            this.endereco.attDadosEndereco(dados.endereco());
        }
    }
}
