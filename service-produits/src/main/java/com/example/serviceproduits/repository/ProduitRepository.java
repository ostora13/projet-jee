package com.example.serviceproduits.repository;

import com.example.serviceproduits.entities.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "produits") // permet de demarrer un web service restfull qui permet de gerer les produits
public interface ProduitRepository extends JpaRepository<Produits,Long> {
}
