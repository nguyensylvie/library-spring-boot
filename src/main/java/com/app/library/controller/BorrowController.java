package com.app.library.controller;

import com.app.library.model.Borrow;
import com.app.library.model.Document;
import com.app.library.model.Member;
import com.app.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Emprunt", description = "API de gestion des emprunts")
@RestController
@RequestMapping("/api/borrows")
public class BorrowController {
    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @Operation(
    summary = "Récupère tous les emprunts",
    description = "Récupère tous les emprunts")
    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @Operation(
    summary = "Récupère un emprunt par ID",
    description = "Obtenez un emprunt en spécifiant son identifiant.")
    @GetMapping("/{borrowId}")
    public Borrow getBorrowById(@PathVariable Long borrowId) {
        return borrowService.getBorrowById(borrowId);
    }

    @Operation(
    summary = "Effectue un emprunt de document par un membre",
    description = "Effectue un emprunt de document par un membre")
    @PostMapping("/{documentId}/{memberId}")
    public Borrow borrowDocument(@PathVariable Long documentId, @PathVariable Long memberId) {
        return borrowService.borrowDocument(documentId, memberId);
    }

    @Operation(
    summary = "Effectue le retour d'un emprunt par ID d'un document",
    description = "Effectue le retour d'un emprunt par ID d'un document")
    @PostMapping("/return/{documentId}")
    public Borrow returnDocument(@PathVariable Long documentId) {
        return borrowService.returnDocument(documentId);
    }

    @Operation(
    summary = "Récupère tous les documents empruntés en cours",
    description = "Récupère tous les documents empruntés en cours")
    @GetMapping("/borrowed")
    public List<Borrow> getBorrowedDocuments() {
        return borrowService.getBorrowedDocuments();
    }

    @Operation(
    summary = "Récupère tous les documents empruntés retournés",
    description = "Récupère tous les documents empruntés retournés")
    @GetMapping("/returned")
    public List<Borrow> getReturnedBorrows() {
        return borrowService.getReturnedBorrows();
    }

    @Operation(
    summary = "Récupère le membre qui a emprunté un document spécifique",
    description = "Récupère le membre qui a emprunté un document spécifique")
    @GetMapping("/document/{documentId}/borrower")
    public Member getBorrowerByDocumentId(@PathVariable Long documentId) {
        return borrowService.getBorrowerByDocumentId(documentId);
    }

    @Operation(
    summary = "Récupère l'ID de l'emprunt d'un document emprunté",
    description = "Récupère l'ID de l'emprunt d'un document emprunté")
    @GetMapping("/document/{documentId}")
    public Long getBorrowIdByDocumentId(@PathVariable Long documentId) {
        return borrowService.getBorrowIdByDocumentId(documentId);
    }

    @Operation(
    summary = "Récupère les documents empruntés d'un membre",
    description = "Récupère les documents empruntés d'un membre")
    @GetMapping("/member/{memberId}/borrowed")
    public List<Document> getBorrowedDocumentsByMemberId(@PathVariable Long memberId) {
        return borrowService.getBorrowedDocumentsByMemberId(memberId);
    }
}
