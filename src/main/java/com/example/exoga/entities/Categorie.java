package com.example.exoga.entities;

import com.example.exoga.requestPojos.CategorieRequestPojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Table(name="categories")
@Entity
public class Categorie {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCategorie;

    @NotNull
    @Getter
    @Setter
    @NotEmpty
    private String nom;

    @Getter
    @Setter
    @NotNull
    private long qt;

    @Getter
    private String dateCreation;

    @Getter
    private String dateModif;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Produit> produits;

    public Categorie(){

    }

    public Categorie(Long idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.setDateCreation();
        this.dateModif = this.dateCreation;
    }

    public Categorie(CategorieRequestPojo catPojo){
        this.nom = catPojo.getNom();
        this.qt = Long.valueOf(catPojo.getQt());
        this.setDateCreation();
        this.dateModif = this.dateCreation;
    }

    public void setDateModif(Date dateModif) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.dateModif = dateFormat.format(dateModif);
    }

    public void setDateCreation() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.dateCreation = dateFormat.format(date);
    }
}
