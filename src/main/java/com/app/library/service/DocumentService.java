package com.app.library.service;

import com.app.library.model.Document;
import com.app.library.repository.DocumentRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final BorrowService borrowService;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, BorrowService borrowService) {
        this.documentRepository = documentRepository;
        this.borrowService = borrowService;
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<Document> getDocumentById(Long documentId) {
        return documentRepository.findById(documentId);
    }

    public Document createDocument(Document document) {
        Year currentYear = Year.now();
        if (document.getReleaseDate() != null && document.getReleaseDate().isAfter(currentYear)) {
            throw new IllegalArgumentException("The release date cannot be later than the current year.");
        }
        document.setRegistrationDate(LocalDate.now());
        return documentRepository.save(document);
    }

    public Document updateDocument(Document updatedDocument) {
        Year currentYear = Year.now();
        if (updatedDocument.getReleaseDate() != null && updatedDocument.getReleaseDate().isAfter(currentYear)) {
            throw new IllegalArgumentException("The release date cannot be later than the current year.");
        }
        return documentRepository.save(updatedDocument);
    }

    public void deleteDocument(Long documentId) {
        borrowService.deleteBorrowsByDocumentId(documentId);
        documentRepository.deleteById(documentId);
    }
}
