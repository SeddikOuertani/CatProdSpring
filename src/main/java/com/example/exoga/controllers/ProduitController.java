package com.example.exoga.controllers;

import com.example.exoga.entities.Categorie;
import com.example.exoga.entities.Produit;
import com.example.exoga.request_pojos.PorduitRequestPojo;
import com.example.exoga.services.CategorieService;
import com.example.exoga.services.ProduitService;
import com.example.exoga.utils.ProduitExcelExporter;
import com.example.exoga.utils.ProduitPDFExporter;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Produit> listProduits = prodService.findAllProduits();

        ProduitExcelExporter excelExporter = new ProduitExcelExporter(listProduits);

        excelExporter.export(response);
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Produit> produitList = prodService.findAllProduits();

        ProduitPDFExporter exporter = new ProduitPDFExporter(produitList);
        exporter.export(response);

    }

    @GetMapping("/{idProd}")
    public ResponseEntity<Produit> findProduitById (@PathVariable("idProd") Long idProd){
        Produit prod = this.prodService.findProduitById(idProd);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

}
