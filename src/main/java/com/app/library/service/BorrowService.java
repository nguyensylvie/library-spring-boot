package com.app.library.service;

import com.app.library.model.Borrow;
import com.app.library.model.Document;
import com.app.library.model.Member;
import com.app.library.repository.BorrowRepository;
import com.app.library.repository.DocumentRepository;
import com.app.library.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowService {
    private final MemberRepository memberRepository;
    private final DocumentRepository documentRepository;
    private final BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(MemberRepository memberRepository, DocumentRepository documentRepository, BorrowRepository borrowRepository) {
        this.memberRepository = memberRepository;
        this.documentRepository = documentRepository;
        this.borrowRepository = borrowRepository;
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowById(Long borrowId) {
        return borrowRepository.findById(borrowId)
                .orElseThrow(() -> new EntityNotFoundException("Borrow not found with ID: " + borrowId));
    }

    public Borrow borrowDocument(Long documentId, Long memberId) {
        // Check if the document exists
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found with ID: " + documentId));

        // Check if the document is available
        if (!document.getAvailable()) {
            throw new IllegalStateException("Document with ID: " + documentId + " is not available for borrowing.");
        }

        // Check if the member exists
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        Borrow borrow = new Borrow();
        borrow.setDocument(document);
        borrow.setMember(member);
        borrow.setLoanDate(LocalDate.now());

        document.setAvailable(false);

        return borrowRepository.save(borrow);
    }

    public Borrow returnDocument(Long documentId) {
        Borrow borrow = borrowRepository.findByDocumentDocumentIdAndReturnDateIsNull(documentId);
        if (borrow != null) {
            if (borrow.getReturnDate() != null) {
                throw new IllegalStateException("Borrow for Document ID: " + documentId + " has already been returned.");
            }
            borrow.setReturnDate(LocalDate.now());
            borrow.getDocument().setAvailable(true);
            return borrowRepository.save(borrow);
        }
        throw new EntityNotFoundException("Borrow not found for Document ID: " + documentId);
    }

    public List<Borrow> getBorrowedDocuments() {
        return borrowRepository.findAllByReturnDateIsNull();
    }

    public List<Borrow> getReturnedBorrows() {
        return borrowRepository.findAllByReturnDateIsNotNull();
    }

    public Member getBorrowerByDocumentId(Long documentId) {
        Borrow borrow = borrowRepository.findByDocumentDocumentIdAndReturnDateIsNull(documentId);
        if (borrow != null) {
            return borrow.getMember();
        }
        return null;
    }

    public Long getBorrowIdByDocumentId(Long documentId) {
        Borrow borrow = borrowRepository.findByDocumentDocumentIdAndReturnDateIsNull(documentId);
        if (borrow != null) {
            return borrow.getBorrowId();
        }
        return null;
    }

    public List<Document> getBorrowedDocumentsByMemberId(Long memberId) {
        List<Borrow> borrows = borrowRepository.findAllByMemberMemberIdAndReturnDateIsNull(memberId);
        List<Document> borrowedDocuments = new ArrayList<>();

        for (Borrow borrow : borrows) {
            borrowedDocuments.add(borrow.getDocument());
        }

        return borrowedDocuments;
    }

    public void deleteBorrowsByMemberId(Long memberId) {
        List<Borrow> borrows = borrowRepository.findAllByMemberMemberId(memberId);
        for (Borrow borrow : borrows) {
            Document document = borrow.getDocument();
            document.setAvailable(true);
            documentRepository.save(document);
            borrowRepository.delete(borrow);
        }
    }

    public void deleteBorrowsByDocumentId(Long documentId) {
        List<Borrow> borrows = borrowRepository.findAllByDocumentDocumentId(documentId);
        for (Borrow borrow : borrows) {
            borrowRepository.delete(borrow);
        }
    }
}