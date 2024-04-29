package com.example.api.medicos.domain.medicos;

import com.example.api.medicos.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name="medicos")
@Entity(name="Medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medicos {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Medicos(DadosCadastroMedico dados) {
        this.crm= dados.crm();
        this.especialidade = dados.especialidade();
        this.nome = dados.nome();
        this.telefone=dados.telefone();
        this.email=dados.email();
        this.endereco= new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void attCadastro(EditCadastroMedicos dados) {
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

    public void setInative(Long id) {
        this.ativo = false;
    }
}