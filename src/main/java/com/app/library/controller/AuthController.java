package com.app.library.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.library.model.Login;
// import com.app.library.service.AuthService;

@RestController
public class AuthController {

    // @Autowired
    // private AuthService authService;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    // @PostMapping("/api/login")
    // public String login(@RequestBody Login login) {
    //     String decodedUsername = new String(Base64.getDecoder().decode(login.getUsername()), StandardCharsets.UTF_8);
    //     String decodedPassword = new String(Base64.getDecoder().decode(login.getPassword()), StandardCharsets.UTF_8);

    //     // Vérifier les informations d'identification avec les informations d'identification valides dans le backend
    //     if (username.equals(decodedUsername) && password.equals(decodedPassword)) {
    //         return "Successful authentication";
    //     } else {
    //         return "Invalid credentials. Try Again.";
    //     }
    // }

    @GetMapping("/api/login")
    public String login() {
        return "Successful authentication";
    }

    // @PostMapping("/api/login")
    // public ResponseEntity<String> login(@RequestBody Login login) {
    //     if (authService.authenticate(login.getUsername(), login.getPassword())) {
    //         // Authentification réussie
    //         return ResponseEntity.ok("Authentification réussie");
    //     } else {
    //         // Authentification échouée
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentification échouée");
    //     }
    // }
}

