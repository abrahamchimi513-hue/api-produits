package com.example.apiproduits;

import io.swagger.v3.oas.annotations.media.Schema;

public record Produit(
        @Schema(description = "Identifiant unique", example = "3") Long id,
        @Schema(description = "Nom du produit", example = "Savon de Marseille") String nom,
        @Schema(description = "Prix en FCFA", example = "500") Integer prix,
        @Schema(description = "Catégorie du produit", example = "Hygiène") String categorie) {
}
