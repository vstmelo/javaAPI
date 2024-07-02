package com.example.api.medicos.domain.medicos;

import com.example.api.medicos.domain.consultas.Consultas;
import com.example.api.medicos.domain.endereco.DadosEndereco;
import com.example.api.medicos.domain.pacientes.DataCreatePacientes;
import com.example.api.medicos.domain.pacientes.Pacientes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicosRepositoryTest {
    @Autowired
    private MedicosRepository medicosRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoDisponivelAleatoriamenteNaData(){

        //arrange
        var dataDaConsulta = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var pacientes = cadastrarPaciente("Paciente", "teste@gmail.com");
        var medico = cadastrarMedico("Dr Hans Schucrutts", "test@gmail.com", "123445", Especialidade.CARDIOLOGIA);
        cadastrarConsulta(medico, pacientes, dataDaConsulta);

        //act
        var medicoLivre = medicosRepository.escolherMedicoDisponivel(Especialidade.CARDIOLOGIA, dataDaConsulta);


        //assert
        assertThat(medicoLivre).isNull();
    }

    private void cadastrarConsulta(Medicos medico, Pacientes paciente, LocalDateTime data) {
        em.persist(new Consultas(null, medico, paciente, data, null));
    }

    private Medicos cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medicos(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Pacientes cadastrarPaciente(String nome, String email) {
        var paciente = new Pacientes(dadosPaciente("Paciente", "test@gmail.com"));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DataCreatePacientes dadosPaciente(String nome, String email) {
        return new DataCreatePacientes(
                nome,
                email,
                "61999999999",
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}