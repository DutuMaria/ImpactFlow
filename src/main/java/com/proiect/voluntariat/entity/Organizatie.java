package com.proiect.voluntariat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(
        name = "organizatie",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_organizatie_email", columnNames = "email")
        }
)
public class Organizatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organizatie")
    private Long id;

    @Size(min = 1, max = 150)
    @Column(name = "nume_organizatie", nullable = false, length = 150)
    private String numeOrganizatie;

    @Email
    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 30)
    @Column(name = "telefon", length = 30)
    private String telefon;

    @Size(max = 200)
    @Column(name = "website", length = 200)
    private String website;

    public Organizatie() {}

    public Long getId() { return id; }
    public String getNumeOrganizatie() { return numeOrganizatie; }
    public String getEmail() { return email; }
    public String getTelefon() { return telefon; }
    public String getWebsite() { return website; }

    public void setId(Long id) { this.id = id; }
    public void setNumeOrganizatie(String numeOrganizatie) { this.numeOrganizatie = numeOrganizatie; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public void setWebsite(String website) { this.website = website; }
}
