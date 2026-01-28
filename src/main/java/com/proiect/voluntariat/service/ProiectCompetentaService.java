package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.ProiectCompetenta;
import com.proiect.voluntariat.entity.ids.ProiectCompetentaId;
import com.proiect.voluntariat.repository.ProiectCompetentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProiectCompetentaService {

    private final ProiectCompetentaRepository repository;

    public ProiectCompetentaService(ProiectCompetentaRepository repository) {
        this.repository = repository;
    }

    public List<ProiectCompetenta> findAll() {
        return repository.findAll();
    }

    public ProiectCompetenta save(ProiectCompetenta pc) {
        try {
            return repository.save(pc);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Nu s-a putut salva: asocierea exista deja sau date invalide.");
        }
    }

    public void deleteById(ProiectCompetentaId id) {
        repository.deleteById(id);
    }
}
