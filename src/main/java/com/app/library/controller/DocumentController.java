package com.app.library.controller;

import com.app.library.model.Document;
import com.app.library.model.DocumentType;
import com.app.library.service.BorrowService;
import com.app.library.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Document", description = "API de gestion des documents")
@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;
    private final BorrowService borrowService;

    @Autowired
    public DocumentController(DocumentService documentService, BorrowService borrowService) {
        this.documentService = documentService;
        this.borrowService = borrowService;
    }

    @Operation(
    summary = "Récupère tous les documents",
    description = "Récupère tous les documents")
    @GetMapping
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @Operation(
    summary = "Récupère un document par ID",
    description = "Obtenez un document en spécifiant son identifiant.")
    @GetMapping("/{documentId}")
    public Optional<Document> getDocumentById(@PathVariable Long documentId) {
        return documentService.getDocumentById(documentId);
    }

    @Operation(
    summary = "Ajoute un nouveau document",
    description = "Ajoute un nouveau document")
    @PostMapping
    public Document createDocument(@RequestBody Document document) {
        return documentService.createDocument(document);
    }

    @Operation(
    summary = "Met à jour un document existant",
    description = "Met à jour un document existant par ID")
    @PutMapping("/{documentId}")
    public Document updateDocument(@PathVariable Long documentId, @RequestBody Document updatedDocument) {
        updatedDocument.setDocumentId(documentId);
        return documentService.updateDocument(updatedDocument);
    }

    @Operation(
    summary = "Supprime un document",
    description = "Supprime un document par ID")
    @DeleteMapping("/{documentId}")
    public void deleteDocument(@PathVariable Long documentId) {
        borrowService.deleteBorrowsByDocumentId(documentId);
        documentService.deleteDocument(documentId);
    }

    @Operation(
    summary = "Récupère tous les types de document",
    description = "Récupère tous les types de document")
    @GetMapping("/document-type")
    public List<String> getAllDocumentTypes() {
        return Arrays.stream(DocumentType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
