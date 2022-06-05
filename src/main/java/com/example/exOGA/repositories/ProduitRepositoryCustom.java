package com.example.exOGA.repositories;

import com.example.exOGA.entities.Produit;

import java.util.List;

public interface ProduitRepositoryCustom {
    List<Produit> findProduitByCategorieId(Long idCat);
}
