package com.example.exOGA.Services;

import com.example.exOGA.Entities.Categorie;
import com.example.exOGA.Repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository catRepo;

    public List<Categorie> findAllCategories(){
        return this.catRepo.findAll();
    }

    public Categorie findCateogorieById(Long idCat){
        return this.catRepo.findById(idCat).get();
    }

    public Categorie ajoutCategorie(Categorie cat){
        long qt = (this.catRepo.count() + 1);
        cat.setQt(qt);
        return this.catRepo.save(cat);
    }

    public Categorie modifierCategorie(Categorie cat){
        Categorie updatedCategorie = this.catRepo.findById(cat.getIdCategorie()).get();
        updatedCategorie.setNom(cat.getNom());
        updatedCategorie.setDateModif(new Date());
        return this.catRepo.save(updatedCategorie);
    }

    public void supprimerCategorie(Long idCat){
        this.catRepo.deleteById(idCat);
    }

}
