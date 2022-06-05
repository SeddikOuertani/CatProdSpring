package com.example.exOGA.services;

import com.example.exOGA.entities.Categorie;
import com.example.exOGA.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository catRepo;

    public List<Categorie> findAllCategories(){
        return this.catRepo.findAll();
    }

    public Categorie findCateogorieById(Long idCat){
        Categorie catRes = null;
        Optional <Categorie> catOpt = this.catRepo.findById(idCat);

        if(catOpt.isPresent()) {
            catRes = catOpt.get();
        }

        return catRes;
    }

    public Categorie ajoutCategorie(Categorie cat){
       return this.catRepo.save(cat);
    }

    public Categorie modifierCategorie(Categorie cat){

        Categorie updatedCategorie = null;
        Optional <Categorie> catOpt = this.catRepo.findById(cat.getIdCategorie());
        if(catOpt.isPresent()){

            updatedCategorie = catOpt.get();
            updatedCategorie.setNom(cat.getNom());
            updatedCategorie.setQt(cat.getQt());
            updatedCategorie.setDateModif(new Date());
            return this.catRepo.save(updatedCategorie);
        }

        return updatedCategorie;

    }

    public void supprimerCategorie(Long idCat){
        this.catRepo.deleteById(idCat);
    }

}
