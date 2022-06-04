package com.example.exOGA.Repositories;

import com.example.exOGA.Entities.Produit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ProduitRepositoryCustomImpl implements ProduitRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Produit> findProduitByCategorieId(Long idCat) {
        Query query = entityManager.createNativeQuery("SELECT * FROM produits WHERE categorie_id_categorie= ?1", Produit.class);
        query.setParameter(1, idCat);
        List<Produit> produits = (List<Produit>) query.getResultList();
        return produits;
    }
}
