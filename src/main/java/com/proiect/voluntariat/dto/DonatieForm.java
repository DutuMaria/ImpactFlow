package com.proiect.voluntariat.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DonatieForm {

    private Long id;

    @NotNull(message = "Proiectul este obligatoriu.")
    private Long proiectId;

    @NotNull(message = "Data donatiei este obligatorie.")
    private LocalDate dataDonatie;

    private String donator;

    @NotNull(message = "Suma este obligatorie.")
    @DecimalMin(value = "0.01", message = "Suma trebuie sa fie mai mare decat 0.")
    private BigDecimal suma;

    public Long getId() { return id; }
    public Long getProiectId() { return proiectId; }
    public LocalDate getDataDonatie() { return dataDonatie; }
    public String getDonator() { return donator; }
    public BigDecimal getSuma() { return suma; }

    public void setId(Long id) { this.id = id; }
    public void setProiectId(Long proiectId) { this.proiectId = proiectId; }
    public void setDataDonatie(LocalDate dataDonatie) { this.dataDonatie = dataDonatie; }
    public void setDonator(String donator) { this.donator = donator; }
    public void setSuma(BigDecimal suma) { this.suma = suma; }
}
