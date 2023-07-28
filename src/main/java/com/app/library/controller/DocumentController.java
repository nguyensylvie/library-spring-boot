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

    @GetMapping
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @GetMapping("/{documentId}")
    public Optional<Document> getDocumentById(@PathVariable Long documentId) {
        return documentService.getDocumentById(documentId);
    }

    @PostMapping
    public Document createDocument(@RequestBody Document document) {
        return documentService.createDocument(document);
    }

    @PutMapping("/{documentId}")
    public Document updateDocument(@PathVariable Long documentId, @RequestBody Document updatedDocument) {
        updatedDocument.setDocumentId(documentId);
        return documentService.updateDocument(updatedDocument);
    }

    @DeleteMapping("/{documentId}")
    public void deleteDocument(@PathVariable Long documentId) {
        borrowService.deleteBorrowsByDocumentId(documentId);
        documentService.deleteDocument(documentId);
    }

    @GetMapping("/document-type")
    public List<String> getAllDocumentTypes() {
        return Arrays.stream(DocumentType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
