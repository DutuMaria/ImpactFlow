package com.proiect.voluntariat.service;

import com.proiect.voluntariat.entity.ParticipareVoluntarProiect;
import com.proiect.voluntariat.entity.ids.ParticipareId;
import com.proiect.voluntariat.repository.ParticipareRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ParticipareService {

    private final ParticipareRepository repository;

    public ParticipareService(ParticipareRepository repository) {
        this.repository = repository;
    }

    public List<ParticipareVoluntarProiect> findAll() {
        return repository.findAll();
    }

    public ParticipareVoluntarProiect findByIdOrThrow(ParticipareId id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Participarea nu exista."));
    }

    public ParticipareVoluntarProiect save(ParticipareVoluntarProiect p) {
        try {
            return repository.save(p);
        } catch (DataIntegrityViolationException ex) {
            // cel mai des: PK compus duplicat sau CK ore>=0
            throw new IllegalStateException("Nu s-a putut salva: exista deja aceasta asociere voluntar-proiect sau date invalide.");
        }
    }

    public void deleteById(ParticipareId id) {
        repository.deleteById(id);
    }
}
