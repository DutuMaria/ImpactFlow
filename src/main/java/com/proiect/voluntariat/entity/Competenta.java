package com.proiect.voluntariat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
        name = "competenta",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_competenta_nume", columnNames = "nume_competenta")
        }
)
public class Competenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_competenta")
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(name = "nume_competenta", nullable = false, length = 120)
    private String numeCompetenta;

    public Competenta() {}

    public Competenta(String numeCompetenta) {
        this.numeCompetenta = numeCompetenta;
    }

    public Long getId() { return id; }
    public String getNumeCompetenta() { return numeCompetenta; }

    public void setId(Long id) { this.id = id; }
    public void setNumeCompetenta(String numeCompetenta) { this.numeCompetenta = numeCompetenta; }
}
