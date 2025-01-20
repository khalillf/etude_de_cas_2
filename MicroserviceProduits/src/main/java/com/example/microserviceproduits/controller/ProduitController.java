package com.example.microserviceproduits.controller;

import com.example.microserviceproduits.model.Produit;
import com.example.microserviceproduits.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produit getProduitById(@PathVariable Long id) {
        return produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit not found"));
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        Produit produit = produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit not found"));
        produit.setNom(produitDetails.getNom());
        produit.setPrix(produitDetails.getPrix());
        return produitRepository.save(produit);
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitRepository.deleteById(id);
    }
}