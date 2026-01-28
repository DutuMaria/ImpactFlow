package com.proiect.voluntariat.entity.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoluntarCompetentaId implements Serializable {

    @Column(name = "id_voluntar")
    private Long voluntarId;

    @Column(name = "id_competenta")
    private Long competentaId;

    public VoluntarCompetentaId() {}

    public VoluntarCompetentaId(Long voluntarId, Long competentaId) {
        this.voluntarId = voluntarId;
        this.competentaId = competentaId;
    }

    public Long getVoluntarId() { return voluntarId; }
    public Long getCompetentaId() { return competentaId; }

    public void setVoluntarId(Long voluntarId) { this.voluntarId = voluntarId; }
    public void setCompetentaId(Long competentaId) { this.competentaId = competentaId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoluntarCompetentaId)) return false;
        VoluntarCompetentaId that = (VoluntarCompetentaId) o;
        return Objects.equals(voluntarId, that.voluntarId) &&
                Objects.equals(competentaId, that.competentaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voluntarId, competentaId);
    }
}
