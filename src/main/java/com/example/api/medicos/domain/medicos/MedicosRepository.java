package com.example.api.medicos.domain.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicosRepository extends JpaRepository<Medicos, Long> {
    Page<Medicos> findAllByAtivoTrue(Pageable pages);
}