package com.example.exOGA.Services;

import com.example.exOGA.Entities.Categorie;
import com.example.exOGA.Entities.Produit;
import com.example.exOGA.Repositories.CategorieRepository;
import com.example.exOGA.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private CategorieRepository catRepo;

    @Autowired
    private ProduitRepository prodRepo;

    public List<Produit> findAllProduits(){
        return this.prodRepo.findAll();
    }

    public Produit findCateogorieById(Long idProd){
        return this.prodRepo.findById(idProd).get();
    }

    public Produit ajoutProduit(Produit prod){
        Categorie categorie = catRepo.getReferenceById(prod.getCategorie().getIdCategorie());
        prod.setCategorie(categorie);
        return this.prodRepo.save(prod);
    }

    public Produit modifierProduit(Produit prod){
        Produit updatedProduit = this.prodRepo.findById(prod.getIdProduit()).get();
        updatedProduit.setNom(prod.getNom());
        updatedProduit.setQt(prod.getQt());
        updatedProduit.setDateModif(new Date());
        return this.prodRepo.save(updatedProduit);
    }

    public void supprimerProduit(Long idProd){
        this.prodRepo.deleteById(idProd);
    }
}
