package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.ParticipareVoluntarProiect;
import com.proiect.voluntariat.entity.ids.ParticipareId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipareRepository extends JpaRepository<ParticipareVoluntarProiect, ParticipareId> {

    @Override
    @EntityGraph(attributePaths = {"voluntar", "proiect"})
    List<ParticipareVoluntarProiect> findAll();
}
