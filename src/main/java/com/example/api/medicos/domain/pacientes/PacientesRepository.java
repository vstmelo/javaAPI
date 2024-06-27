package com.example.api.medicos.domain.pacientes;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacientesRepository extends JpaRepository<Pacientes, Long> {
    Page<Pacientes> findAllByAtivoTrue(Pageable pages);

    @Query("""
            select p.ativo
            from Pacientes p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
}
