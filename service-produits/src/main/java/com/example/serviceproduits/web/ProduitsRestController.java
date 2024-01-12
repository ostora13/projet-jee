package com.example.serviceproduits.web;

import com.example.serviceproduits.entities.Produits;
import com.example.serviceproduits.repository.ProduitRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProduitsRestController implements HealthIndicator {

    private ProduitRepository produitRepository;
    public ProduitsRestController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/produits")
    public List<Produits> produitsList(){

        return produitRepository.findAll();
    }


    @GetMapping("/produits/{id}")
    public Produits commandesById(@PathVariable Long id){

        return produitRepository.findById(id).get();
    }


    @Override
    public Health health() {
        System.out.println("****** Actuator : ProductController health() ");
        List<Produits> products = produitRepository.findAll();
        if (products.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }
}
