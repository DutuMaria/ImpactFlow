package com.proiect.voluntariat.repository;

import com.proiect.voluntariat.entity.ProiectCompetenta;
import com.proiect.voluntariat.entity.ids.ProiectCompetentaId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProiectCompetentaRepository extends JpaRepository<ProiectCompetenta, ProiectCompetentaId> {

    @Override
    @EntityGraph(attributePaths = {"proiect", "competenta"})
    List<ProiectCompetenta> findAll();
}
