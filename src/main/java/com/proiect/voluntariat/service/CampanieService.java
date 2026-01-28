package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.Campanie;
import com.proiect.voluntariat.repository.CampanieRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CampanieService {
    private final CampanieRepository campanieRepository;

    public CampanieService(CampanieRepository campanieRepository) {
        this.campanieRepository = campanieRepository;
    }

    public List<Campanie> findAll() {
        return campanieRepository.findAll();
    }

    public Campanie findByIdOrThrow(Long id) {
        return campanieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Campania nu exista (id=" + id + ")"));
    }

    public Campanie save(Campanie campanie) {
        // validare business (în plus fata de CHECK din DB)
        if (campanie.getDataFinal() != null && campanie.getDataStart() != null
                && campanie.getDataFinal().isBefore(campanie.getDataStart())) {
            throw new IllegalStateException("Data finala trebuie sa fie dupa sau egala cu data de start.");
        }

        try {
            return campanieRepository.save(campanie);
        } catch (DataIntegrityViolationException ex) {
            // daca se loveste de CHECK sau alte constrangeri
            throw new IllegalStateException("Date invalide pentru campanie (verifica status si intervalul de date).");
        }
    }

    public void deleteById(Long id) {
        try {
            campanieRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            // daca e referita de proiecte
            throw new IllegalStateException("Nu poti sterge campania: este folosita în proiecte.");
        }
    }
}
