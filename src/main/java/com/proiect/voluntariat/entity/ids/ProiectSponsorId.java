package com.proiect.voluntariat.entity.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProiectSponsorId implements Serializable {

    @Column(name = "id_proiect")
    private Long proiectId;

    @Column(name = "id_sponsor")
    private Long sponsorId;

    public ProiectSponsorId() {}

    public ProiectSponsorId(Long proiectId, Long sponsorId) {
        this.proiectId = proiectId;
        this.sponsorId = sponsorId;
    }

    public Long getProiectId() { return proiectId; }
    public Long getSponsorId() { return sponsorId; }

    public void setProiectId(Long proiectId) { this.proiectId = proiectId; }
    public void setSponsorId(Long sponsorId) { this.sponsorId = sponsorId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProiectSponsorId)) return false;
        ProiectSponsorId that = (ProiectSponsorId) o;
        return Objects.equals(proiectId, that.proiectId) &&
                Objects.equals(sponsorId, that.sponsorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proiectId, sponsorId);
    }
}
