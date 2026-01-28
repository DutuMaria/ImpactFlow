package com.proiect.voluntariat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "locatie")
public class Locatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locatie")
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "adresa", nullable = false, length = 200)
    private String adresa;

    @NotBlank
    @Size(max = 100)
    @Column(name = "oras", nullable = false, length = 100)
    private String oras;

    @NotBlank
    @Size(max = 100)
    @Column(name = "judet", nullable = false, length = 100)
    private String judet;

    public Locatie() {}

    public Locatie(String adresa, String oras, String judet) {
        this.adresa = adresa;
        this.oras = oras;
        this.judet = judet;
    }

    public Long getId() { return id; }
    public String getAdresa() { return adresa; }
    public String getOras() { return oras; }
    public String getJudet() { return judet; }

    public void setId(Long id) { this.id = id; }
    public void setAdresa(String adresa) { this.adresa = adresa; }
    public void setOras(String oras) { this.oras = oras; }
    public void setJudet(String judet) { this.judet = judet; }
}
