package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.ProiectSponsor;
import com.proiect.voluntariat.entity.ids.ProiectSponsorId;
import com.proiect.voluntariat.repository.ProiectSponsorRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProiectSponsorService {

    private final ProiectSponsorRepository repository;

    public ProiectSponsorService(ProiectSponsorRepository repository) {
        this.repository = repository;
    }

    public List<ProiectSponsor> findAll() {
        return repository.findAll();
    }

    public ProiectSponsor findByIdOrThrow(ProiectSponsorId id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asocierea proiect-sponsor nu exista."));
    }

    public ProiectSponsor save(ProiectSponsor ps) {
        try {
            return repository.save(ps);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Nu s-a putut salva: asocierea exista deja sau date invalide.");
        }
    }

    public void deleteById(ProiectSponsorId id) {
        repository.deleteById(id);
    }
}
