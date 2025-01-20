package com.example.microservicecommandes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import com.example.microservicecommandes.model.Commande;
import com.example.microservicecommandes.repository.CommandeRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class MicroserviceCommandesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCommandesApplication.class, args);
    }

    /**
     * CommandLineRunner to insert sample Commande entries on startup.
     */
    @Bean
    CommandLineRunner initData(CommandeRepository commandeRepository) {
        return args -> {
            // Create and save some sample Commande objects
            commandeRepository.save(new Commande(null, "Commande A", 2, "2025-01-20", 150.0, 1L));
            commandeRepository.save(new Commande(null, "Commande B", 5, "2025-01-21", 100.0, 2L));
            commandeRepository.save(new Commande(null, "Commande C", 1, "2025-01-22", 999.99, 5L));
            commandeRepository.save(new Commande(null, "Commande D", 10, "2025-01-23", 230.0, 3L));
            commandeRepository.save(new Commande(null, "Commande E", 3, "2025-01-24", 300.0, 2L));
            commandeRepository.save(new Commande(null, "Commande F", 7, "2025-01-25", 700.0, 1L));
            commandeRepository.save(new Commande(null, "Commande G", 6, "2025-01-26", 720.5, 9L));
            commandeRepository.save(new Commande(null, "Commande H", 4, "2025-01-27", 460.0, 4L));
            commandeRepository.save(new Commande(null, "Commande I", 2, "2025-01-28", 123.45, 3L));
            commandeRepository.save(new Commande(null, "Commande J", 5, "2025-01-29", 640.0, 10L));

            // Print them out in the console
            commandeRepository.findAll().forEach(cmd -> {
                System.out.println(cmd.getId() + " - " + cmd.getDescription()
                        + " [" + cmd.getQuantite() + " items]"
                        + " => Montant: " + cmd.getMontant()
                        + " (Produit ID: " + cmd.getIdProduit() + ")");
            });
        };
    }
}
