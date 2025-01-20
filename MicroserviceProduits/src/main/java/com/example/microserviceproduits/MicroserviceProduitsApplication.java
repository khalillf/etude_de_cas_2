package com.example.microserviceproduits;

import com.example.microserviceproduits.model.Produit;
import com.example.microserviceproduits.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceProduitsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceProduitsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProduitRepository produitRepository) {
        return args -> {
            // Save 10 sample products in the H2 database
            produitRepository.save(new Produit("Laptop", 999.99));
            produitRepository.save(new Produit("Smartphone", 799.00));
            produitRepository.save(new Produit("Headphones", 199.99));
            produitRepository.save(new Produit("Wireless Mouse", 29.99));
            produitRepository.save(new Produit("Mechanical Keyboard", 79.99));
            produitRepository.save(new Produit("Gaming Chair", 189.00));
            produitRepository.save(new Produit("External SSD", 120.50));
            produitRepository.save(new Produit("Smartwatch", 250.00));
            produitRepository.save(new Produit("Tablet", 329.49));
            produitRepository.save(new Produit("Monitor 27-inch", 219.00));

            // Print them to confirm
            produitRepository.findAll().forEach(produit -> {
                System.out.println(
                        produit.getId() + " - " + produit.getNom() + " : " + produit.getPrix()
                );
            });
        };
    }
}
