package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.VoluntarCompetenta;
import com.proiect.voluntariat.entity.ids.VoluntarCompetentaId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoluntarCompetentaRepository extends JpaRepository<VoluntarCompetenta, VoluntarCompetentaId> {

    @Override
    @EntityGraph(attributePaths = {"voluntar", "competenta"})
    List<VoluntarCompetenta> findAll();
}
