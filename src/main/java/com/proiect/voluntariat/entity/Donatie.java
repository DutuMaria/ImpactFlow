package com.proiect.voluntariat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "donatie")
public class Donatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donatie")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proiect", nullable = false)
    private Proiect proiect;

    @NotNull
    @Column(name = "data_donatie", nullable = false)
    private LocalDate dataDonatie;

    @Column(name = "donator", length = 150)
    private String donator;

    @NotNull
    @Column(name = "suma", nullable = false, precision = 12, scale = 2)
    private BigDecimal suma;

    public Donatie() {}

    public Long getId() { return id; }
    public Proiect getProiect() { return proiect; }
    public LocalDate getDataDonatie() { return dataDonatie; }
    public String getDonator() { return donator; }
    public BigDecimal getSuma() { return suma; }

    public void setId(Long id) { this.id = id; }
    public void setProiect(Proiect proiect) { this.proiect = proiect; }
    public void setDataDonatie(LocalDate dataDonatie) { this.dataDonatie = dataDonatie; }
    public void setDonator(String donator) { this.donator = donator; }
    public void setSuma(BigDecimal suma) { this.suma = suma; }
}
