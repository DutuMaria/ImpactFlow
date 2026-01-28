package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.ProiectSponsor;
import com.proiect.voluntariat.entity.ids.ProiectSponsorId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProiectSponsorRepository extends JpaRepository<ProiectSponsor, ProiectSponsorId> {

    @Override
    @EntityGraph(attributePaths = {"proiect", "sponsor"})
    List<ProiectSponsor> findAll();
}
