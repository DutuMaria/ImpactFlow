package com.proiect.voluntariat.entity;

import com.proiect.voluntariat.entity.enums.NumeCategorieVoluntar;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categorie_voluntar")
public class CategorieVoluntar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie_voluntar")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nume_categorie", nullable = false, length = 50)
    private NumeCategorieVoluntar numeCategorie;

    @Size(max = 1000)
    @Column(name = "descriere", length = 1000)
    private String descriere;

    public CategorieVoluntar() {}

    public Long getId() { return id; }
    public NumeCategorieVoluntar getNumeCategorie() { return numeCategorie; }
    public String getDescriere() { return descriere; }

    public void setId(Long id) { this.id = id; }
    public void setNumeCategorie(NumeCategorieVoluntar numeCategorie) { this.numeCategorie = numeCategorie; }
    public void setDescriere(String descriere) { this.descriere = descriere; }
}
