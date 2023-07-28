package com.app.library.repository;

import com.app.library.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findAllByReturnDateIsNull();
    List<Borrow> findAllByReturnDateIsNotNull();
    Borrow findByDocumentDocumentIdAndReturnDateIsNull(Long documentId);
    List<Borrow> findAllByMemberMemberIdAndReturnDateIsNull(Long memberId);
    List<Borrow> findAllByMemberMemberId(Long memberId);
    List<Borrow> findAllByDocumentDocumentId(Long documentId);

}
