package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.Proiect;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProiectRepository extends JpaRepository<Proiect, Long> {

    @EntityGraph(attributePaths = {"organizatie", "locatie", "campanie"})
    List<Proiect> findAll();
}
