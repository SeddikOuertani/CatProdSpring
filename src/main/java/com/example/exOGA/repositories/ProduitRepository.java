package com.example.exOGA.repositories;

import com.example.exOGA.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>, ProduitRepositoryCustom {

}
