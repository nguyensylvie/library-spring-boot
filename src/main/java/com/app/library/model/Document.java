package com.app.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    private String title;

    private String author;

    private Year releaseDate;

    private Boolean available = true;

    private LocalDate registrationDate;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
}
