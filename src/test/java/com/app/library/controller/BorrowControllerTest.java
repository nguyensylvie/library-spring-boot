package com.app.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAllBorrows() throws Exception {
        mockMvc.perform(get("/api/borrows"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andReturn();
    }

    @Test
    public void testGetBorrowById() throws Exception {
        Long borrowId = 1L;
        mockMvc.perform(get("/api/borrows/{borrowId}", borrowId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.borrowId").value(borrowId));
    }

    @Test
    public void testBorrowDocument() throws Exception {
        Long documentId = 3L;
        Long memberId = 3L;

        mockMvc.perform(post("/api/borrows/{documentId}/{memberId}", documentId, memberId))
               .andExpect(status().isOk())
               .andReturn();
    }

    @Test
    public void testReturnDocument() throws Exception {
        Long documentId = 3L;

        mockMvc.perform(post("/api/borrows/return/{documentId}", documentId))
               .andExpect(status().isOk())
               .andReturn();
    }

    @Test
    public void testGetBorrowedDocuments() throws Exception {
        mockMvc.perform(get("/api/borrows/borrowed"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andReturn();
    }

    @Test
    public void testGetReturnedBorrows() throws Exception {
        mockMvc.perform(get("/api/borrows/returned"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andReturn();
    }

    @Test
    public void testGetBorrowerByDocumentId() throws Exception {
        Long documentId = 1L;

        mockMvc.perform(get("/api/borrows/document/{documentId}/borrower", documentId))
               .andExpect(status().isOk())
               .andReturn();
    }

    @Test
    public void testGetBorrowIdByDocumentId() throws Exception {
        Long documentId = 1L;

        mockMvc.perform(get("/api/borrows/document/{documentId}", documentId))
               .andExpect(status().isOk())
               .andReturn();
    }

    @Test
    public void testGetBorrowedDocumentsByMemberId() throws Exception {
        Long memberId = 1L;

        mockMvc.perform(get("/api/borrows/member/{memberId}/borrowed", memberId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andReturn();
    }
}
