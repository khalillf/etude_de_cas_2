package com.example.microservicecommandes.controller;

import com.example.microservicecommandes.model.Commande;
import com.example.microservicecommandes.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallbackGetCommandeById")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande not found"));
    }

    public Commande fallbackGetCommandeById(Long id) {
        return new Commande(); // Retourne une commande vide en cas d'erreur
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }

    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande not found"));
        commande.setDescription(commandeDetails.getDescription());
        commande.setQuantite(commandeDetails.getQuantite());
        commande.setDate(commandeDetails.getDate());
        commande.setMontant(commandeDetails.getMontant());
        commande.setIdProduit(commandeDetails.getIdProduit());
        return commandeRepository.save(commande);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeRepository.deleteById(id);
    }
}