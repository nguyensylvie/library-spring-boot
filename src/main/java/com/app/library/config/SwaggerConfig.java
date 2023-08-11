package com.app.library.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

  @Value("${library.openapi.dev-url}")
  private String devUrl;

  @Value("${library.openapi.prod-url}")
  private String prodUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setEmail("snguyen@consultants-solutec.fr");
    contact.setName("Sylvie Nguyen");

    Info info = new Info()
        .title("Gestion de bibliothèque API")
        .version("1.0")
        .contact(contact)
        .description("Cette API expose les points de terminaison du projet de gestion de bibliothèque.");

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}