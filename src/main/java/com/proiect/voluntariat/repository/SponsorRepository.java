package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
    Optional<Sponsor> findByEmail(String email);
    }