package com.app.library.controller;

import com.app.library.model.Borrow;
import com.app.library.model.Document;
import com.app.library.model.Member;
import com.app.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {
    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("/{borrowId}")
    public Borrow getBorrowById(@PathVariable Long borrowId) {
        return borrowService.getBorrowById(borrowId);
    }

    @PostMapping("/{documentId}/{memberId}")
    public Borrow borrowDocument(@PathVariable Long documentId, @PathVariable Long memberId) {
        return borrowService.borrowDocument(documentId, memberId);
    }

    @PostMapping("/return/{documentId}")
    public Borrow returnDocument(@PathVariable Long documentId) {
        return borrowService.returnDocument(documentId);
    }

    @GetMapping("/borrowed")
    public List<Borrow> getBorrowedDocuments() {
        return borrowService.getBorrowedDocuments();
    }

    @GetMapping("/returned")
    public List<Borrow> getReturnedBorrows() {
        return borrowService.getReturnedBorrows();
    }

    @GetMapping("/document/{documentId}/borrower")
    public Member getBorrowerByDocumentId(@PathVariable Long documentId) {
        return borrowService.getBorrowerByDocumentId(documentId);
    }

    @GetMapping("/document/{documentId}")
    public Long getBorrowIdByDocumentId(@PathVariable Long documentId) {
        return borrowService.getBorrowIdByDocumentId(documentId);
    }

    @GetMapping("/member/{memberId}/borrowed")
    public List<Document> getBorrowedDocumentsByMemberId(@PathVariable Long memberId) {
        return borrowService.getBorrowedDocumentsByMemberId(memberId);
    }
}
