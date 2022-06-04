package com.example.exOGA.Controllers;

import com.example.exOGA.Entities.Categorie;
import com.example.exOGA.Services.CategorieService;
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

    @RequestMapping(value = "/ajouter", method = RequestMethod.POST)
    public ResponseEntity<Categorie> ajouterCategorie (@Valid @RequestBody Categorie cat){
        Categorie categorie = new Categorie(cat.getNom(), cat.getQt());
        Categorie savedCategorie = this.catService.ajoutCategorie(categorie);
        return new ResponseEntity<Categorie>(savedCategorie, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{idCat}")
    public ResponseEntity<Categorie> findCategorieById (@PathVariable("idCat") Long idCat){
        Categorie cat = this.catService.findCateogorieById(idCat);
        return new ResponseEntity<Categorie>(cat, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Categorie>> findAllCategories (){
        List<Categorie> categories = this.catService.findAllCategories();
        return new ResponseEntity<List<Categorie>>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/supprimer/{idCat}")
    public ResponseEntity<Categorie> deleteCategorieById (@PathVariable("idCat") Long idCat){
        Categorie deletedCat = this.catService.findCateogorieById(idCat);
        this.catService.supprimerCategorie(idCat);
        return new ResponseEntity<Categorie>(deletedCat, HttpStatus.OK);
    }

    @PutMapping("/modifier/{idCat}")
    public ResponseEntity<Categorie> deleteAllCategories (@PathVariable("idCat") Long idCat, @Valid @RequestBody Categorie categorie){
        Categorie newCategorie = new Categorie(idCat, categorie.getNom());
        Categorie updatedCategorie = this.catService.modifierCategorie(newCategorie);
        return  new ResponseEntity<Categorie>(updatedCategorie, HttpStatus.OK);
    }

}
