package com.example.exOGA.Repositories;

import com.example.exOGA.Entities.Produit;

import java.util.List;

public interface ProduitRepositoryCustom {
    List<Produit> findProduitByCategorieId(Long idCat);
}
