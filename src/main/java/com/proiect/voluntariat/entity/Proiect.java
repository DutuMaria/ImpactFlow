package com.proiect.voluntariat.entity;

import com.proiect.voluntariat.entity.enums.ProiectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "proiect")
public class Proiect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proiect")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizatie", nullable = false)
    private Organizatie organizatie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_campanie") // nullable
    private Campanie campanie;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_locatie", nullable = false)
    private Locatie locatie;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nume_proiect", nullable = false, length = 150)
    private String numeProiect;

    @Size(max = 1000)
    @Column(name = "descriere", length = 1000)
    private String descriere;

    @NotNull
    @Column(name = "data_start", nullable = false)
    private LocalDate dataStart;

    @Column(name = "data_final")
    private LocalDate dataFinal;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private ProiectStatus status;

    public Proiect() {}

    public Long getId() { return id; }
    public Organizatie getOrganizatie() { return organizatie; }
    public Campanie getCampanie() { return campanie; }
    public Locatie getLocatie() { return locatie; }
    public String getNumeProiect() { return numeProiect; }
    public String getDescriere() { return descriere; }
    public LocalDate getDataStart() { return dataStart; }
    public LocalDate getDataFinal() { return dataFinal; }
    public ProiectStatus getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setOrganizatie(Organizatie organizatie) { this.organizatie = organizatie; }
    public void setCampanie(Campanie campanie) { this.campanie = campanie; }
    public void setLocatie(Locatie locatie) { this.locatie = locatie; }
    public void setNumeProiect(String numeProiect) { this.numeProiect = numeProiect; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public void setDataStart(LocalDate dataStart) { this.dataStart = dataStart; }
    public void setDataFinal(LocalDate dataFinal) { this.dataFinal = dataFinal; }
    public void setStatus(ProiectStatus status) { this.status = status; }

}
