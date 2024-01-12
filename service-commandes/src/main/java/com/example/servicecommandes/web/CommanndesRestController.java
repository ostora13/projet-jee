package com.example.servicecommandes.web;

import com.example.servicecommandes.client.ProduitsRestClient;
import com.example.servicecommandes.configuration.ApplicationPropertiesConfiguration;
import com.example.servicecommandes.entities.Commandes;
import com.example.servicecommandes.model.Produits;
import com.example.servicecommandes.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommanndesRestController implements HealthIndicator {

    private CommandeRepository commandeRepository;
    private ProduitsRestClient produitsRestClient;

    @Autowired
    ApplicationPropertiesConfiguration applicationPropertiesConfiguration;

    public CommanndesRestController(CommandeRepository commandeRepository, ProduitsRestClient produitsRestClient) {
        this.commandeRepository = commandeRepository;
        this.produitsRestClient = produitsRestClient;
    }




    @GetMapping("/commandes")
    public List<Commandes> commandesList(){

        List<Commandes> commandes = commandeRepository.findAll();
        commandes.forEach(c->{
            c.setProduits(produitsRestClient.findProduitsById(c.getProduitId()));
        });

        List<Commandes> listeLimite = commandes.subList(0,applicationPropertiesConfiguration.getLimitDeProduits());
        System.out.println(listeLimite);
        return listeLimite;
    }


    @GetMapping("/commandes/{id}")
    public Commandes commandesById(@PathVariable Long id){

        Commandes commande =  commandeRepository.findById(id).get();
        Produits produit = produitsRestClient.findProduitsById(commande.getProduitId());
        System.out.println(produit.toString());
        commande.setProduits(produit);

        return commande;

    }


    @Override
    public Health health() {
        System.out.println("****** Actuator : ProductController health() ");
        List<Commandes> products = commandeRepository.findAll();
        if (products.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }
}
