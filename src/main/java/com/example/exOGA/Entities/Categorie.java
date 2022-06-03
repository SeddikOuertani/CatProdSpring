package com.example.exOGA.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Table(name="categories")
@Entity
public class Categorie {

    private final String DATE_FORMAT = "yyyy-MM-dd";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCategorie;

    @NotNull
    @NotEmpty
    private String nom;

    private long qt;

    private String dateCreation;

    private String dateModif;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

    public Categorie(){

    }

    public Categorie(Long idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
    }

    public Categorie(String nom) {
        this.nom = nom;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.dateCreation = dateFormat.format(date);
        this.dateModif= dateFormat.format(date);
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getQt() {
        return qt;
    }

    public void setQt(long qt) {
        this.qt = qt;
    }

    public String getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.dateModif = dateFormat.format(dateModif);
    }
}
