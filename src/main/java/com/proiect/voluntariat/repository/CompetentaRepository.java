package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.Competenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompetentaRepository extends JpaRepository<Competenta, Long> {
    Optional<Competenta> findByNumeCompetentaIgnoreCase(String numeCompetenta);
}