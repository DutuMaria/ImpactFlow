package com.proiect.voluntariat.entity;

import com.proiect.voluntariat.entity.ids.ParticipareId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "participare_voluntar_proiect")
public class ParticipareVoluntarProiect {
    @EmbeddedId
    private ParticipareId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("voluntarId")
    @JoinColumn(name = "id_voluntar", nullable = false)
    private Voluntar voluntar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("proiectId")
    @JoinColumn(name = "id_proiect", nullable = false)
    private Proiect proiect;

    @NotBlank
    @Column(name = "rol", nullable = false, length = 80)
    private String rol;

    @NotNull
    @Column(name = "numar_ore", nullable = false, precision = 7, scale = 2)
    private BigDecimal numarOre;

    public ParticipareVoluntarProiect() {}

    public ParticipareId getId() { return id; }
    public Voluntar getVoluntar() { return voluntar; }
    public Proiect getProiect() { return proiect; }
    public String getRol() { return rol; }
    public BigDecimal getNumarOre() { return numarOre; }

    public void setId(ParticipareId id) { this.id = id; }
    public void setVoluntar(Voluntar voluntar) { this.voluntar = voluntar; }
    public void setProiect(Proiect proiect) { this.proiect = proiect; }
    public void setRol(String rol) { this.rol = rol; }
    public void setNumarOre(BigDecimal numarOre) { this.numarOre = numarOre; }
}
