package com.proiect.voluntariat.entity;

import com.proiect.voluntariat.entity.enums.NivelCompetenta;
import com.proiect.voluntariat.entity.ids.VoluntarCompetentaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "voluntar_competenta")
public class VoluntarCompetenta {

    @EmbeddedId
    private VoluntarCompetentaId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("voluntarId")
    @JoinColumn(name = "id_voluntar", nullable = false)
    private Voluntar voluntar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("competentaId")
    @JoinColumn(name = "id_competenta", nullable = false)
    private Competenta competenta;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false, length = 30)
    private NivelCompetenta nivel;

    public VoluntarCompetenta() {}

    public VoluntarCompetentaId getId() { return id; }
    public Voluntar getVoluntar() { return voluntar; }
    public Competenta getCompetenta() { return competenta; }
    public NivelCompetenta getNivel() { return nivel; }

    public void setId(VoluntarCompetentaId id) { this.id = id; }
    public void setVoluntar(Voluntar voluntar) { this.voluntar = voluntar; }
    public void setCompetenta(Competenta competenta) { this.competenta = competenta; }
    public void setNivel(NivelCompetenta nivel) { this.nivel = nivel; }
}
