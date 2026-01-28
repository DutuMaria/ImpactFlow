package com.proiect.voluntariat.entity.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProiectCompetentaId implements Serializable {

    @Column(name = "id_proiect")
    private Long proiectId;

    @Column(name = "id_competenta")
    private Long competentaId;

    public ProiectCompetentaId() {}

    public ProiectCompetentaId(Long proiectId, Long competentaId) {
        this.proiectId = proiectId;
        this.competentaId = competentaId;
    }

    public Long getProiectId() { return proiectId; }
    public Long getCompetentaId() { return competentaId; }

    public void setProiectId(Long proiectId) { this.proiectId = proiectId; }
    public void setCompetentaId(Long competentaId) { this.competentaId = competentaId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProiectCompetentaId)) return false;
        ProiectCompetentaId that = (ProiectCompetentaId) o;
        return Objects.equals(proiectId, that.proiectId) &&
                Objects.equals(competentaId, that.competentaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proiectId, competentaId);
    }
}
