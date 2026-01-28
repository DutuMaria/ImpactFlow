package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.CategorieVoluntar;
import com.proiect.voluntariat.repository.CategorieVoluntarRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategorieVoluntarService {
    private final CategorieVoluntarRepository repository;

    public CategorieVoluntarService(CategorieVoluntarRepository repository) {
        this.repository = repository;
    }

    public List<CategorieVoluntar> findAll() {
        return repository.findAll();
    }

    public CategorieVoluntar findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria nu exista (id=" + id + ")"));
    }

    public CategorieVoluntar save(CategorieVoluntar categorie) {
        try {
            return repository.save(categorie);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Date invalide pentru categoria de voluntar.");
        }
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            // daca exista voluntari legati
            throw new IllegalStateException("Nu poti sterge categoria: este folosita de voluntari.");
        }
    }
}
