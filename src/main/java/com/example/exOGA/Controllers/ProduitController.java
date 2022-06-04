package com.example.exOGA.Controllers;

import com.example.exOGA.Entities.Categorie;
import com.example.exOGA.Entities.Produit;
import com.example.exOGA.Services.CategorieService;
import com.example.exOGA.Services.ProduitService;
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

    @RequestMapping(value = "/ajouter", method = RequestMethod.POST)
    public ResponseEntity<Produit> ajouterProduit (@Valid @RequestBody Produit prod){
        Categorie cat = this.catService.findCateogorieById(prod.getCategorie().getIdCategorie());
        Produit produit = new Produit(prod.getNom(),prod.isDisponible(),prod.getQt(), cat);
        Produit savedProduit = this.prodService.ajoutProduit(produit);
        return new ResponseEntity<Produit>(savedProduit, HttpStatus.OK);
    }

    @GetMapping("/{idProd}")
    public ResponseEntity<Produit> findProduitById (@PathVariable("idProd") Long idProd){
        Produit prod = this.prodService.findProduitById(idProd);
        return new ResponseEntity<Produit>(prod, HttpStatus.OK);
    }

    @GetMapping("/bycategorie/{idCat}")
    public ResponseEntity<List<Produit>> findByCategorieId (@PathVariable("idCat") Long idCat){
        List<Produit> produits = this.prodService.findProduitsByCategorieId(idCat);
        return new ResponseEntity<List<Produit>>(produits, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Produit>> findAllProduits (){
        List<Produit> produits = this.prodService.findAllProduits();
        return new ResponseEntity<List<Produit>>(produits, HttpStatus.OK);
    }

    @DeleteMapping("/supprimer/{idProd}")
    public ResponseEntity<Produit> deleteProdById (@PathVariable("idProd") Long idProd){
        Produit deletedProd = this.prodService.findProduitById(idProd);
        this.prodService.supprimerProduit(idProd);
        return new ResponseEntity<Produit>(deletedProd, HttpStatus.OK);
    }

    @PutMapping("/modifier/{idProd}")
    public ResponseEntity<Produit> modifierProduit (@PathVariable("idProd") Long idProd, @Valid @RequestBody Produit produit){
        Produit newProduit = new Produit(idProd, produit.getNom(), produit.getQt(), produit.isDisponible(), produit.getCategorie());
        Produit updatedProduit = this.prodService.modifierProduit(newProduit);
        return  new ResponseEntity<Produit>(updatedProduit, HttpStatus.OK);
    }

}
