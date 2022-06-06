package com.example.exoga.services;

import com.example.exoga.entities.Categorie;
import com.example.exoga.entities.Produit;
import com.example.exoga.repositories.CategorieRepository;
import com.example.exoga.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private CategorieRepository catRepo;

    @Autowired
    private ProduitRepository prodRepo;

    public List<Produit> findAllProduits(){
        return this.prodRepo.findAll();
    }

    public Produit findProduitById(Long idProd){
        Produit prod = null;
        Optional<Produit> prodOpt = this.prodRepo.findById(idProd);
        if(prodOpt.isPresent()){
            prod = prodOpt.get();
        }
        return prod;
    }

    public Produit ajoutProduit(Produit prod){
        Categorie categorie = catRepo.getReferenceById(prod.getCategorie().getIdCategorie());
        prod.setCategorie(categorie);
        return this.prodRepo.save(prod);
    }

    public Produit modifierProduit(Produit prod){
        Categorie cat = prod.getCategorie();
        Produit updatedProduit = null;
        Optional<Produit> prodOpt = this.prodRepo.findById(prod.getIdProduit());

        if(prodOpt.isPresent()){
            updatedProduit = this.prodRepo.findById(prod.getIdProduit()).get();
            updatedProduit.setNom(prod.getNom());
            updatedProduit.setQt(prod.getQt());
            updatedProduit.setDateModif(new Date());
            updatedProduit.setCategorie(cat);
            return this.prodRepo.save(updatedProduit);
        }

        return updatedProduit;
    }

    public void supprimerProduit(Long idProd){
        this.prodRepo.deleteById(idProd);
    }

    public List<Produit> findProduitsByCategorieId(Long idCat){
        return this.prodRepo.findProduitByCategorieId(idCat);
    }
}
