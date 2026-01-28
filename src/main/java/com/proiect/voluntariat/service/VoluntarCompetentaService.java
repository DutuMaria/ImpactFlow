package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.VoluntarCompetenta;
import com.proiect.voluntariat.entity.ids.VoluntarCompetentaId;
import com.proiect.voluntariat.repository.VoluntarCompetentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VoluntarCompetentaService {

    private final VoluntarCompetentaRepository repository;

    public VoluntarCompetentaService(VoluntarCompetentaRepository repository) {
        this.repository = repository;
    }

    public List<VoluntarCompetenta> findAll() {
        return repository.findAll();
    }

    public VoluntarCompetenta findByIdOrThrow(VoluntarCompetentaId id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asocierea voluntar-competenta nu exista."));
    }

    public VoluntarCompetenta save(VoluntarCompetenta vc) {
        try {
            return repository.save(vc);
        } catch (DataIntegrityViolationException ex) {
            // duplicat PK compus sau CHECK nivel
            throw new IllegalStateException("Nu s-a putut salva: exista deja aceasta asociere sau date invalide.");
        }
    }

    public void deleteById(VoluntarCompetentaId id) {
        repository.deleteById(id);
    }
}
