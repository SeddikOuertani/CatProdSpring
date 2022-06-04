package com.example.exOGA.Repositories;

import com.example.exOGA.Entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>, ProduitRepositoryCustom {

}
