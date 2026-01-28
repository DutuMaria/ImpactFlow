package com.proiect.voluntariat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(
        name = "voluntar",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_voluntar_email", columnNames = "email")
        }
)
public class Voluntar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voluntar")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie_voluntar", nullable = false)
    private CategorieVoluntar categorieVoluntar;

    @NotBlank
    @Size(max = 80)
    @Column(name = "nume", nullable = false, length = 80)
    private String nume;

    @NotBlank
    @Size(max = 80)
    @Column(name = "prenume", nullable = false, length = 80)
    private String prenume;

    @Email
    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;

    @Size(max = 30)
    @Column(name = "telefon", length = 30)
    private String telefon;

    @Column(name = "data_nasterii")
    private LocalDate dataNasterii;

    public Voluntar() {}

    public Long getId() { return id; }
    public CategorieVoluntar getCategorieVoluntar() { return categorieVoluntar; }
    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }
    public String getEmail() { return email; }
    public String getTelefon() { return telefon; }
    public LocalDate getDataNasterii() { return dataNasterii; }

    public void setId(Long id) { this.id = id; }
    public void setCategorieVoluntar(CategorieVoluntar categorieVoluntar) { this.categorieVoluntar = categorieVoluntar; }
    public void setNume(String nume) { this.nume = nume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public void setDataNasterii(LocalDate dataNasterii) { this.dataNasterii = dataNasterii; }
}
