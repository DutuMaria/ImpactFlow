package com.proiect.voluntariat.entity;

import com.proiect.voluntariat.entity.enums.TipSponsor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(
        name = "sponsor",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_sponsor_email", columnNames = "email")
        }
)
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sponsor")
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nume_sponsor", nullable = false, length = 150)
    private String numeSponsor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tip_sponsor", nullable = false, length = 30)
    private TipSponsor tipSponsor;

    @Email
    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 30)
    @Column(name = "telefon", length = 30)
    private String telefon;

    public Sponsor() {}

    public Long getId() { return id; }
    public String getNumeSponsor() { return numeSponsor; }
    public TipSponsor getTipSponsor() { return tipSponsor; }
    public String getEmail() { return email; }
    public String getTelefon() { return telefon; }

    public void setId(Long id) { this.id = id; }
    public void setNumeSponsor(String numeSponsor) { this.numeSponsor = numeSponsor; }
    public void setTipSponsor(TipSponsor tipSponsor) { this.tipSponsor = tipSponsor; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
}
