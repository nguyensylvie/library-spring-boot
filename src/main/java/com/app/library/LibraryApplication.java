package com.app.library;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
        // Utiliser la variable d'environnement PORT ou utiliser le port 9000 par défaut
        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "9000";
        }

        // Configurer le port
        SpringApplication app = new SpringApplication(LibraryApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        app.run(args);
    }

}
