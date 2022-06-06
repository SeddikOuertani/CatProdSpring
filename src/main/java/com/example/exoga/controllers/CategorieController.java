package com.example.exoga.controllers;

import com.example.exoga.entities.Categorie;
import com.example.exoga.request_pojos.CategorieRequestPojo;
import com.example.exoga.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategorieController {

    @Autowired
    private CategorieService catService;

    @PostMapping("/ajouter")
    public ResponseEntity<Categorie> ajouterCategorie (@Valid @RequestBody CategorieRequestPojo catPojo){
        Categorie categorie = new Categorie(catPojo);
        Categorie savedCategorie = this.catService.ajoutCategorie(categorie);
        return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{idCat}")
    public ResponseEntity<Categorie> findCategorieById (@PathVariable("idCat") Long idCat){

        Categorie cat = this.catService.findCateogorieById(idCat);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Categorie>> findAllCategories (){
        List<Categorie> categories = this.catService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/supprimer/{idCat}")
    public ResponseEntity<Categorie> deleteCategorieById (@PathVariable("idCat") Long idCat){
        Categorie deletedCat = this.catService.findCateogorieById(idCat);
        this.catService.supprimerCategorie(idCat);
        return new ResponseEntity<>(deletedCat, HttpStatus.OK);
    }

    @PutMapping("/modifier/{idCat}")
    public ResponseEntity<Categorie> deleteAllCategories (@PathVariable("idCat") Long idCat, @Valid @RequestBody CategorieRequestPojo catPojo){
        System.out.println(catPojo.toString());
        Categorie newCategorie = new Categorie(catPojo);
        newCategorie.setIdCategorie(idCat);
        Categorie updatedCategorie = this.catService.modifierCategorie(newCategorie);
        return  new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
    }

}
