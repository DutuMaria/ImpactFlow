package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.Competenta;
import com.proiect.voluntariat.repository.CompetentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompetentaService {

    private final CompetentaRepository repository;

    public CompetentaService(CompetentaRepository repository) {
        this.repository = repository;
    }

    public List<Competenta> findAll() {
        return repository.findAll();
    }

    public Competenta findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competenta nu exista (id=" + id + ")"));
    }

    public Competenta save(Competenta competenta) {
        String nume = competenta.getNumeCompetenta() == null ? null : competenta.getNumeCompetenta().trim();
        competenta.setNumeCompetenta(nume);

        if (nume != null && !nume.isEmpty()) {
            var existing = repository.findByNumeCompetentaIgnoreCase(nume);
            if (existing.isPresent() && (competenta.getId() == null || !existing.get().getId().equals(competenta.getId()))) {
                throw new IllegalStateException("Exista deja o competenta cu acest nume.");
            }
        }

        try {
            return repository.save(competenta);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Nu s-a putut salva competenta (posibil duplicat).");
        }
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            // daca e folosita în voluntar_competenta / proiect_competenta
            throw new IllegalStateException("Nu poti sterge competenta: este folosita în alte înregistrari.");
        }
    }
}
