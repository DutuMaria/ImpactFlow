package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.Organizatie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizatieRepository extends JpaRepository<Organizatie, Long> {
    Optional<Organizatie> findByEmail(String email);
}