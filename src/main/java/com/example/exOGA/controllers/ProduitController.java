package com.example.exOGA.controllers;

import com.example.exOGA.entities.Categorie;
import com.example.exOGA.entities.Produit;
import com.example.exOGA.request_pojos.PorduitRequestPojo;
import com.example.exOGA.services.CategorieService;
import com.example.exOGA.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produits")
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController {

    @Autowired
    private ProduitService prodService;

    @Autowired
    private CategorieService catService;

    @PostMapping("/ajouter")
    public ResponseEntity<Produit> ajouterProduit (@Valid @RequestBody PorduitRequestPojo prodPojo){

        Categorie cat = this.catService.findCateogorieById(Long.valueOf(prodPojo.getCategorie().getIdCategorie()));

        Produit prod = new Produit(prodPojo);
        prod.setCategorie(cat);

        Produit savedProduit = this.prodService.ajoutProduit(prod);
        return new ResponseEntity<>(savedProduit, HttpStatus.OK);
    }

    @GetMapping("/{idProd}")
    public ResponseEntity<Produit> findProduitById (@PathVariable("idProd") Long idProd){
        Produit prod = this.prodService.findProduitById(idProd);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @GetMapping("/bycategorie/{idCat}")
    public ResponseEntity<List<Produit>> findByCategorieId (@PathVariable("idCat") Long idCat){
        List<Produit> produits = this.prodService.findProduitsByCategorieId(idCat);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Produit>> findAllProduits (){
        List<Produit> produits = this.prodService.findAllProduits();
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @DeleteMapping("/supprimer/{idProd}")
    public ResponseEntity<Produit> deleteProdById (@PathVariable("idProd") Long idProd){
        Produit deletedProd = this.prodService.findProduitById(idProd);
        this.prodService.supprimerProduit(idProd);
        return new ResponseEntity<>(deletedProd, HttpStatus.OK);
    }

    @PutMapping("/modifier/{idProd}")
    public ResponseEntity<Produit> modifierProduit (@PathVariable("idProd") Long idProd, @Valid @RequestBody PorduitRequestPojo prodPojo){

        Categorie cat = this.catService.findCateogorieById(Long.valueOf(prodPojo.getCategorie().getIdCategorie()));

        Produit produit = new Produit(prodPojo);
        produit.setCategorie(cat);
        produit.setIdProduit(idProd);

        Produit savedProduit = this.prodService.modifierProduit(produit);
        return  new ResponseEntity<>(savedProduit, HttpStatus.OK);
    }

}
