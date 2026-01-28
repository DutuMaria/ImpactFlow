package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.Donatie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonatieRepository extends JpaRepository<Donatie, Long> {

    @EntityGraph(attributePaths = {"proiect"})
    List<Donatie> findAll();
}
