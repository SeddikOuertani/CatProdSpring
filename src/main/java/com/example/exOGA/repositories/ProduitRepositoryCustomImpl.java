package com.example.exOGA.repositories;

import com.example.exOGA.entities.Produit;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Produit> findProduitByCategorieId(Long idCat) {
        Query query = entityManager.createNativeQuery("SELECT * FROM produits WHERE categorie_id_categorie= ?1", Produit.class);
        query.setParameter(1, idCat);
        return query.getResultList();
    }
}
