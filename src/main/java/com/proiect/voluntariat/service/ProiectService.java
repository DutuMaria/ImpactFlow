package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.Proiect;
import com.proiect.voluntariat.repository.ProiectRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProiectService {

    private final ProiectRepository proiectRepository;

    public ProiectService(ProiectRepository proiectRepository) {
        this.proiectRepository = proiectRepository;
    }

    public List<Proiect> findAll() {
        return proiectRepository.findAll();
    }

    public Proiect findByIdOrThrow(Long id) {
        return proiectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proiectul nu exista (id=" + id + ")"));
    }

    public Proiect save(Proiect proiect) {
        if (proiect.getDataFinal() != null && proiect.getDataStart() != null
                && proiect.getDataFinal().isBefore(proiect.getDataStart())) {
            throw new IllegalStateException("Data finala trebuie sa fie dupa sau egala cu data de start.");
        }

        try {
            return proiectRepository.save(proiect);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Nu s-a putut salva proiectul (verifica datele si statusul).");
        }
    }

    public void deleteById(Long id) {
        try {
            proiectRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Nu poti sterge proiectul: are legaturi (donatii/participari/etc.).");
        }
    }
}
