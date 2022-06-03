package com.example.exOGA.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name="produits")
@Entity
public class Produit {

    private final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduit;

    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    @NotEmpty
    private long qt;

    @NotNull
    private boolean disponible;

    private String dateCreation;

    private String dateModif;

    @NotNull
    @ManyToOne
    private Categorie categorie;

   public Produit(){

   }

    public Produit(Long idProduit, String nom, long qt, boolean disponible, Categorie categorie) {
        this.idProduit = idProduit;
        this.qt = qt;
        this.nom = nom;
        this.disponible = disponible;
        this.categorie = categorie;
    }

    public Produit(String nom, boolean disponible, long qt, Categorie categorie) {
        this.nom = nom;
        this.disponible = disponible;
        this.categorie = categorie;
        this.qt = qt;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        System.out.println(dateFormat.format(date));
        this.dateCreation = dateFormat.format(date);
        this.dateModif= dateFormat.format(date);
    }


    public Long getIdProduit() {
        return idProduit;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.dateModif = dateFormat.format(dateModif);
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
