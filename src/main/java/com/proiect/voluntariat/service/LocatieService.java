package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.Locatie;
import com.proiect.voluntariat.repository.LocatieRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LocatieService {
    private final LocatieRepository locatieRepository;

    public LocatieService(LocatieRepository locatieRepository) {
        this.locatieRepository = locatieRepository;
    }

    public List<Locatie> findAll() {
        return locatieRepository.findAll();
    }

    public Locatie findByIdOrThrow(Long id) {
        return locatieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Locatia nu exista (id=" + id + ")"));
    }

    public Locatie save(Locatie locatie) {
        return locatieRepository.save(locatie);
    }

    public void deleteById(Long id) {
        try {
            locatieRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            // apare daca locatia e folosita în PROIECT (FK)
            throw new IllegalStateException("Nu poti sterge locatia: este folosita de unul sau mai multe proiecte.");
        }
    }
}
