package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.Donatie;
import com.proiect.voluntariat.repository.DonatieRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DonatieService {

    private final DonatieRepository repository;

    public DonatieService(DonatieRepository repository) {
        this.repository = repository;
    }

    public List<Donatie> findAll() {
        return repository.findAll();
    }

    public Donatie findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Donatia nu exista (id=" + id + ")"));
    }

    public Donatie save(Donatie donatie) {
        // mic cleanup: donator gol -> null
        if (donatie.getDonator() != null && donatie.getDonator().trim().isEmpty()) {
            donatie.setDonator(null);
        }
        try {
            return repository.save(donatie);
        } catch (DataIntegrityViolationException ex) {
            // include CK suma>0 sau FK proiect
            throw new IllegalStateException("Nu s-a putut salva donatia. Verifica suma si proiectul.");
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
