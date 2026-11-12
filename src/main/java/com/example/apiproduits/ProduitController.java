package com.example.apiproduits;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@Tag(name = "Produits", description = "Catalogue des produits de la boutique du quartier")
public class ProduitController {

    // Données en mémoire : elles disparaissent quand l'application redémarre
    private final List<Produit> produits = new ArrayList<>(List.of(
            new Produit(1L, "Riz parfumé 5 kg", 4500, "Alimentation"),
            new Produit(2L, "Huile de palme 1 L", 1200, "Alimentation"),
            new Produit(3L, "Savon de Marseille", 500, "Hygiène")));

    @Operation(summary = "Lister tous les produits",
            description = "Retourne l'ensemble des produits disponibles à la boutique.")
    @ApiResponse(responseCode = "200", description = "Liste des produits")
    @GetMapping
    public List<Produit> lister() {
        return produits;
    }

    @Operation(summary = "Trouver un produit par son id",
            description = "Retourne le produit correspondant à l'identifiant fourni.")
    @ApiResponse(responseCode = "200", description = "Produit trouvé")
    @ApiResponse(responseCode = "404", description = "Produit introuvable", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Produit> trouver(
            @Parameter(description = "Identifiant du produit", example = "1") @PathVariable Long id) {
        return produits.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter un produit",
            description = "Ajoute un produit au catalogue. L'identifiant est attribué automatiquement.")
    @ApiResponse(responseCode = "201", description = "Produit créé")
    @PostMapping
    public ResponseEntity<Produit> ajouter(@RequestBody Produit produit) {
        Produit nouveau = new Produit((long) produits.size() + 1, produit.nom(), produit.prix(), produit.categorie());
        produits.add(nouveau);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveau);
    }
}
