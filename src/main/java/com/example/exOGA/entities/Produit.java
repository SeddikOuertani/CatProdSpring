package com.example.exOGA.entities;

import com.example.exOGA.request_pojos.PorduitRequestPojo;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(name="produits")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "categorie")
@Entity
public class Produit {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduit;

    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String nom;

    @Getter
    @Setter
    @NotNull
    private long qt;

    @Getter
    @Setter
    @NotNull
    private boolean disponible;

    @Getter
    private String dateCreation;

    @Getter
    private String dateModif;

    @Getter
    @Setter
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

    public Produit(PorduitRequestPojo prodPojo) {
        this.nom = prodPojo.getNom();
        this.qt = Long.valueOf(prodPojo.getQt());
        this.disponible = Boolean.valueOf(prodPojo.getDisponible());
        this.setDateCreation();
        this.dateModif = this.dateCreation;
    }

    public Produit(String nom, boolean disponible, long qt, Categorie categorie) {
        this.nom = nom;
        this.disponible = disponible;
        this.categorie = categorie;
        this.qt = qt;
        this.setDateCreation();
        this.dateModif= this.dateCreation;
    }

    public void setDateModif(Date dateModif) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.dateModif = dateFormat.format(dateModif);
    }

    public void setDateCreation(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.dateCreation = dateFormat.format(date);
    }

}
