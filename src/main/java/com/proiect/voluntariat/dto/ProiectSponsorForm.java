package com.proiect.voluntariat.dto;

import jakarta.validation.constraints.NotNull;

public class ProiectSponsorForm {

    @NotNull(message = "Proiectul este obligatoriu.")
    private Long proiectId;

    @NotNull(message = "Sponsorul este obligatoriu.")
    private Long sponsorId;

    public Long getProiectId() { return proiectId; }
    public Long getSponsorId() { return sponsorId; }

    public void setProiectId(Long proiectId) { this.proiectId = proiectId; }
    public void setSponsorId(Long sponsorId) { this.sponsorId = sponsorId; }
}
