package com.example.servicecommandes.repository;

import com.example.servicecommandes.entities.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource(path = "commandes")// permet de demarrer un web service restfull qui permet de gerer les commandes
public interface CommandeRepository extends JpaRepository<Commandes,Long> {
}
