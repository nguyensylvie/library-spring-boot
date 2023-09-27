package com.app.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.library.model.Document;
import com.app.library.model.DocumentType;
import com.app.library.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc; 
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private DocumentService documentService;

    @Test
    public void testGetAllDocuments() throws Exception {
        mockMvc.perform(get("/api/documents"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$").isArray());
//	        .andExpect(jsonPath("$[0].title", is("Bel-Ami")));
    }

    @Test
    public void testGetDocumentById() throws Exception {
        Long documentId = 2L; 
        mockMvc.perform(get("/api/documents/{documentId}", documentId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.documentId").value(documentId));
    }

    @Test
    public void testCreateDocument() throws Exception {
        Document newDocument = new Document();
        newDocument.setTitle("Nouveau document"); 
        newDocument.setAuthor("Auteur"); 
        newDocument.setReleaseDate(Year.of(2020)); 
        newDocument.setDocumentType(DocumentType.BOOK);
        newDocument.setAvailable(true);
        newDocument.setRegistrationDate(LocalDate.now());

        String jsonContent = objectMapper.writeValueAsString(newDocument);
        
        mockMvc.perform(post("/api/documents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testUpdateDocument() throws Exception {
        Optional<Document> existingDocumentOptional = documentService.getDocumentById(1L);
        if (existingDocumentOptional.isPresent()) {
            Document existingDocument = existingDocumentOptional.get();
            existingDocument.setTitle("Document mis à jour");

            String jsonContent = objectMapper.writeValueAsString(existingDocument);

            mockMvc.perform(put("/api/documents/{documentId}", existingDocument.getDocumentId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContent))
                    .andExpect(status().isOk());
        }
    }
    
    @Test
    public void testDeleteDocument() throws Exception {
    	Optional<Document> documentToDeleteOptional = documentService.getDocumentById(1L);

    	if (documentToDeleteOptional.isPresent()) {
			 Document documentToDelete = documentToDeleteOptional.get();
	         mockMvc.perform(delete("/api/documents/{documentId}", documentToDelete.getDocumentId()))
	                .andExpect(status().isOk());
    	}
    }
   
}
