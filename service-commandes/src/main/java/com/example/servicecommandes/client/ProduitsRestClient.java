package com.example.servicecommandes.client;

import com.example.servicecommandes.model.Produits;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUITS-SERVICE")
public interface ProduitsRestClient {

    @CircuitBreaker(name = "produitService", fallbackMethod = "getDefaultProduits")
    @GetMapping("/api/produits/{id}")
    Produits findProduitsById(@PathVariable Long id);

    @CircuitBreaker(name = "produitService", fallbackMethod = "getAllProduits")
    @GetMapping("/api/produits")
    List<Produits> allProduits();

    default Produits getDefaultProduits(Long id, Exception exception) {
        Produits produits = new Produits();
        produits.setId(id);
        produits.setName("Not Vailable");
        produits.setDescription("Not Vailable");

        return produits;
    }

    default List<Produits> getAllProduits(Exception exception) {

        return List.of();

    }
}
