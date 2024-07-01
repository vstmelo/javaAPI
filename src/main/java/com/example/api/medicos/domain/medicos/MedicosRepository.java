package com.example.api.medicos.domain.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicosRepository extends JpaRepository<Medicos, Long> {
    Page<Medicos> findAllByAtivoTrue(Pageable pages);

    @Query("""
            select m from Medicos m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consultas c
                where
                c.data = :data
            and
            c.motivoCancelamento is null
            )
            order by rand()
            limit 1
            """)
    Medicos escolherMedicoDisponivel(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo
            from Medicos m
            where
            m.id = :id
            """)
    Boolean findAtivoBy(Long id);
}