package com.proiect.voluntariat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class VoluntarForm {
    private Long id;

    @NotNull(message = "Categoria este obligatorie.")
    private Long categorieVoluntarId;

    @NotBlank
    @Size(max = 80)
    private String nume;

    @NotBlank
    @Size(max = 80)
    private String prenume;

    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 30)
    private String telefon;

    private LocalDate dataNasterii;

    public Long getId() { return id; }
    public Long getCategorieVoluntarId() { return categorieVoluntarId; }
    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }
    public String getEmail() { return email; }
    public String getTelefon() { return telefon; }
    public LocalDate getDataNasterii() { return dataNasterii; }

    public void setId(Long id) { this.id = id; }
    public void setCategorieVoluntarId(Long categorieVoluntarId) { this.categorieVoluntarId = categorieVoluntarId; }
    public void setNume(String nume) { this.nume = nume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public void setDataNasterii(LocalDate dataNasterii) { this.dataNasterii = dataNasterii; }
}
