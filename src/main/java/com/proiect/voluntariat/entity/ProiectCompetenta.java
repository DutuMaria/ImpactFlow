package com.proiect.voluntariat.entity;

import com.proiect.voluntariat.entity.ids.ProiectCompetentaId;
import jakarta.persistence.*;

@Entity
@Table(name = "proiect_competenta")
public class ProiectCompetenta {

    @EmbeddedId
    private ProiectCompetentaId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("proiectId")
    @JoinColumn(name = "id_proiect", nullable = false)
    private Proiect proiect;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("competentaId")
    @JoinColumn(name = "id_competenta", nullable = false)
    private Competenta competenta;

    public ProiectCompetenta() {}

    public ProiectCompetentaId getId() { return id; }
    public Proiect getProiect() { return proiect; }
    public Competenta getCompetenta() { return competenta; }

    public void setId(ProiectCompetentaId id) { this.id = id; }
    public void setProiect(Proiect proiect) { this.proiect = proiect; }
    public void setCompetenta(Competenta competenta) { this.competenta = competenta; }
}
