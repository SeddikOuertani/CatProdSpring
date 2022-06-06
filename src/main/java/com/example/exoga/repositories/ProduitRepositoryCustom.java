package com.example.exoga.repositories;

import com.example.exoga.entities.Produit;

import java.util.List;

public interface ProduitRepositoryCustom {
    List<Produit> findProduitByCategorieId(Long idCat);
}
