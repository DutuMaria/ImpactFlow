package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.Sponsor;
import com.proiect.voluntariat.repository.SponsorRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SponsorService {

    private final SponsorRepository sponsorRepository;

    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    public List<Sponsor> findAll() {
        return sponsorRepository.findAll();
    }

    public Sponsor findByIdOrThrow(Long id) {
        return sponsorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sponsorul nu exista (id=" + id + ")"));
    }

    public Sponsor save(Sponsor sponsor) {
        if (sponsor.getEmail() != null && sponsor.getEmail().trim().isEmpty()) {
            sponsor.setEmail(null);
        }
        try {
            return sponsorRepository.save(sponsor);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Email-ul exista deja sau datele sunt invalide.");
        }
    }

    public void deleteById(Long id) {
        try {
            sponsorRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            // daca e folosit în proiect_sponsor
            throw new IllegalStateException("Nu poti sterge sponsorul: este asociat cu proiecte.");
        }
    }
}
