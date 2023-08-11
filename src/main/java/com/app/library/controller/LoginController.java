// package com.app.library.controller;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.nio.charset.StandardCharsets;
// import java.util.Base64;

// import org.springframework.beans.factory.annotation.Value;
// import com.app.library.model.Login;

// @RestController
// @RequestMapping("/api/login")
// public class LoginController {

//     @Value("${spring.security.user.name}")
//     private String adminUsername;

//     @Value("${spring.security.user.password}")
//     private String adminPassword;
    
//     @PostMapping
//     public String login(@RequestBody Login login) {
//         String decodedUsername = new String(Base64.getDecoder().decode(login.getUsername()), StandardCharsets.UTF_8);
//         String decodedPassword = new String(Base64.getDecoder().decode(login.getPassword()), StandardCharsets.UTF_8);

//         // Vérifier les informations d'identification avec les informations d'identification valides dans le backend
//         if (adminUsername.equals(decodedUsername) && adminPassword.equals(decodedPassword)) {
//             return "Successful authentication";
//         } else {
//             return "Invalid credentials. Try Again.";
//         }
//     }

//     // @GetMapping
//     // public String login() {
//     //     return "Successful authentication";
//     // }
// }