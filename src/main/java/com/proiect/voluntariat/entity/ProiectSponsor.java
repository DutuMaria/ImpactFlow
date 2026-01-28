package com.proiect.voluntariat.entity;

import com.proiect.voluntariat.entity.ids.ProiectSponsorId;
import jakarta.persistence.*;

@Entity
@Table(name = "proiect_sponsor")
public class ProiectSponsor {

    @EmbeddedId
    private ProiectSponsorId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("proiectId")
    @JoinColumn(name = "id_proiect", nullable = false)
    private Proiect proiect;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("sponsorId")
    @JoinColumn(name = "id_sponsor", nullable = false)
    private Sponsor sponsor;

    public ProiectSponsor() {}

    public ProiectSponsorId getId() { return id; }
    public Proiect getProiect() { return proiect; }
    public Sponsor getSponsor() { return sponsor; }

    public void setId(ProiectSponsorId id) { this.id = id; }
    public void setProiect(Proiect proiect) { this.proiect = proiect; }
    public void setSponsor(Sponsor sponsor) { this.sponsor = sponsor; }
}
