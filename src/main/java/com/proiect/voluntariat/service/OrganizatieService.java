package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.Organizatie;
import com.proiect.voluntariat.repository.OrganizatieRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrganizatieService {
    private final OrganizatieRepository organizatieRepository;

    public OrganizatieService(OrganizatieRepository organizatieRepository) {
        this.organizatieRepository = organizatieRepository;
    }

    public List<Organizatie> findAll() {
        return organizatieRepository.findAll();
    }

    public Organizatie findByIdOrThrow(Long id) {
        return organizatieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Organizatia nu exista (id=" + id + ")"));
    }

    public Organizatie save(Organizatie organizatie) {
        // curatare mica (optional): email gol -> null, ca sa nu se bata în unique
        if (organizatie.getEmail() != null && organizatie.getEmail().trim().isEmpty()) {
            organizatie.setEmail(null);
        }
        try {
            return organizatieRepository.save(organizatie);
        } catch (DataIntegrityViolationException ex) {
            // cel mai des: email duplicat sau FK (mai tarziu, la delete)
            throw new IllegalStateException("Email-ul exista deja. Alege alt email sau lasa gol.");
        }
    }

    public void deleteById(Long id) {
        try {
            organizatieRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            // daca exista proiecte care refera organizatia
            throw new IllegalStateException("Nu poti sterge organizatia: este folosita în proiecte.");
        }
    }
}
