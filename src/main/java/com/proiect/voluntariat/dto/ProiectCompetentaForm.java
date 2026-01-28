package com.proiect.voluntariat.dto;

import jakarta.validation.constraints.NotNull;

public class ProiectCompetentaForm {

    @NotNull(message = "Proiectul este obligatoriu.")
    private Long proiectId;

    @NotNull(message = "Competenta este obligatorie.")
    private Long competentaId;

    public Long getProiectId() { return proiectId; }
    public Long getCompetentaId() { return competentaId; }

    public void setProiectId(Long proiectId) { this.proiectId = proiectId; }
    public void setCompetentaId(Long competentaId) { this.competentaId = competentaId; }
}
