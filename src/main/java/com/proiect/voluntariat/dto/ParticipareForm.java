package com.proiect.voluntariat.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ParticipareForm {

    @NotNull(message = "Voluntarul este obligatoriu.")
    private Long voluntarId;

    @NotNull(message = "Proiectul este obligatoriu.")
    private Long proiectId;

    @NotBlank(message = "Rolul este obligatoriu.")
    private String rol;

    @NotNull(message = "Numarul de ore este obligatoriu.")
    @DecimalMin(value = "0.00", message = "Numarul de ore trebuie sa fie >= 0.")
    private BigDecimal numarOre;

    public Long getVoluntarId() { return voluntarId; }
    public Long getProiectId() { return proiectId; }
    public String getRol() { return rol; }
    public BigDecimal getNumarOre() { return numarOre; }

    public void setVoluntarId(Long voluntarId) { this.voluntarId = voluntarId; }
    public void setProiectId(Long proiectId) { this.proiectId = proiectId; }
    public void setRol(String rol) { this.rol = rol; }
    public void setNumarOre(BigDecimal numarOre) { this.numarOre = numarOre; }
}
