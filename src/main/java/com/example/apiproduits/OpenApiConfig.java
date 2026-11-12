package com.example.apiproduits;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("API Produits")
                .version("1.0")
                .description("API de la boutique du quartier : consulter et ajouter des produits"));
    }
}
