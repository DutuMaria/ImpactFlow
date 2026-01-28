package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.Voluntar;
import com.proiect.voluntariat.repository.VoluntarRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VoluntarService {

    private final VoluntarRepository voluntarRepository;

    public VoluntarService(VoluntarRepository voluntarRepository) {
        this.voluntarRepository = voluntarRepository;
    }

    public List<Voluntar> findAll() {
        return voluntarRepository.findAll();
    }

    public Voluntar findByIdOrThrow(Long id) {
        return voluntarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voluntarul nu exista (id=" + id + ")"));
    }

    public Voluntar save(Voluntar voluntar) {
        if (voluntar.getEmail() != null && voluntar.getEmail().trim().isEmpty()) {
            voluntar.setEmail(null);
        }
        try {
            return voluntarRepository.save(voluntar);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Email-ul exista deja sau datele sunt invalide.");
        }
    }

    public void deleteById(Long id) {
        try {
            voluntarRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            // daca e folosit în participare / voluntar_competenta etc.
            throw new IllegalStateException("Nu poti sterge voluntarul: are legaturi în alte tabele.");
        }
    }
}
