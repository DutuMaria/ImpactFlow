package com.proiect.voluntariat.dto;

import com.proiect.voluntariat.entity.enums.ProiectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ProiectForm {

    private Long id;

    @NotNull(message = "Organizatia este obligatorie.")
    private Long organizatieId;

    // campanie e optionala
    private Long campanieId;

    @NotNull(message = "Locatia este obligatorie.")
    private Long locatieId;

    @NotBlank
    @Size(max = 150)
    private String numeProiect;

    @Size(max = 1000)
    private String descriere;

    @NotNull
    private LocalDate dataStart;

    private LocalDate dataFinal;

    @NotNull
    private ProiectStatus status;

    public Long getId() { return id; }
    public Long getOrganizatieId() { return organizatieId; }
    public Long getCampanieId() { return campanieId; }
    public Long getLocatieId() { return locatieId; }
    public String getNumeProiect() { return numeProiect; }
    public String getDescriere() { return descriere; }
    public LocalDate getDataStart() { return dataStart; }
    public LocalDate getDataFinal() { return dataFinal; }
    public ProiectStatus getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setOrganizatieId(Long organizatieId) { this.organizatieId = organizatieId; }
    public void setCampanieId(Long campanieId) { this.campanieId = campanieId; }
    public void setLocatieId(Long locatieId) { this.locatieId = locatieId; }
    public void setNumeProiect(String numeProiect) { this.numeProiect = numeProiect; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
    public void setDataStart(LocalDate dataStart) { this.dataStart = dataStart; }
    public void setDataFinal(LocalDate dataFinal) { this.dataFinal = dataFinal; }
    public void setStatus(ProiectStatus status) { this.status = status; }
}
