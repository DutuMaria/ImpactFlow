package com.proiect.voluntariat.entity;

import com.proiect.voluntariat.entity.enums.CampanieStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "campanie")
public class Campanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campanie")
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nume_campanie", nullable = false, length = 150)
    private String numeCampanie;

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
    private CampanieStatus status;

    public Campanie() {}

    public Long getId() { return id; }
    public String getNumeCampanie() { return numeCampanie; }
    public String getDescriere() { return descriere; }
    public LocalDate getDataStart() { return dataStart; }
    public LocalDate getDataFinal() { return dataFinal; }
    public CampanieStatus getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setNumeCampanie(String numeCampanie) { this.numeCampanie = numeCampanie; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public void setDataStart(LocalDate dataStart) { this.dataStart = dataStart; }
    public void setDataFinal(LocalDate dataFinal) { this.dataFinal = dataFinal; }
    public void setStatus(CampanieStatus status) { this.status = status; }
}
