package com.proiect.voluntariat.entity.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipareId implements Serializable {

    @Column(name = "id_voluntar")
    private Long voluntarId;

    @Column(name = "id_proiect")
    private Long proiectId;

    public ParticipareId() {}

    public ParticipareId(Long voluntarId, Long proiectId) {
        this.voluntarId = voluntarId;
        this.proiectId = proiectId;
    }

    public Long getVoluntarId() { return voluntarId; }
    public Long getProiectId() { return proiectId; }

    public void setVoluntarId(Long voluntarId) { this.voluntarId = voluntarId; }
    public void setProiectId(Long proiectId) { this.proiectId = proiectId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipareId)) return false;
        ParticipareId that = (ParticipareId) o;
        return Objects.equals(voluntarId, that.voluntarId) &&
                Objects.equals(proiectId, that.proiectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voluntarId, proiectId);
    }
}
