package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.Voluntar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoluntarRepository extends JpaRepository<Voluntar, Long> {
    Optional<Voluntar> findByEmail(String email);
}