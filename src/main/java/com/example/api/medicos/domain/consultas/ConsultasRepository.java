package com.example.api.medicos.domain.consultas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultasRepository extends JpaRepository<Consultas, Long> {
    Boolean existsByMedicoAndData(Long idMedico, LocalDateTime data);

    Boolean existsByPacienteAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
