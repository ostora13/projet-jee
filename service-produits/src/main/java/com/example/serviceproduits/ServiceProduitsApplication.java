package com.example.serviceproduits;

import com.example.serviceproduits.entities.Produits;
import com.example.serviceproduits.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ServiceProduitsApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceProduitsApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProduitRepository produitRepository){
        return args -> {
            List<Produits> produitsList = List.of(
                     Produits.builder()
                            .name("Produit A")
                            .description("c'est un produit A")
                            .build(),

                    Produits.builder()
                            .name("Produit B")
                            .description("c'est un produit B")
                            .build(),

                    Produits.builder()
                            .name("Produit C")
                            .description("c'est un produit c")
                            .build(),

                    Produits.builder()
                            .name("Produit D")
                            .description("c'est un produit D")
                            .build(),

                    Produits.builder()
                            .name("Produit E")
                            .description("c'est un produit E")
                            .build()
            );

            produitRepository.saveAll(produitsList);
        };
    }

}
