// package com.app.library.dataloader;

// import com.app.library.model.Borrow;
// import com.app.library.model.Document;
// import com.app.library.model.DocumentType;
// import com.app.library.model.Member;
// import com.app.library.repository.BorrowRepository;
// import com.app.library.repository.DocumentRepository;
// import com.app.library.repository.MemberRepository;
// import jakarta.annotation.PostConstruct;
// import org.springframework.stereotype.Component;

// import java.time.LocalDate;
// import java.time.Year;

// @Component
// public class DataLoader {
//     private final DocumentRepository documentRepository;
//     private final MemberRepository memberRepository;

//     private final BorrowRepository borrowRepository;

//     public DataLoader(DocumentRepository documentRepository, MemberRepository memberRepository, BorrowRepository borrowRepository) {
//         this.documentRepository = documentRepository;
//         this.memberRepository = memberRepository;
//         this.borrowRepository = borrowRepository;
//     }

//     @PostConstruct
//     public void init() {
//         Document document1 = new Document(null, "Harry Potter à l'école des sorciers", "J.K. Rowling", Year.of(2017), true, LocalDate.now(), DocumentType.valueOf("BOOK"));
//         documentRepository.save(document1);

//         Document document2 = new Document(null, "L'Etranger", "Albert Camus", Year.of(1942), true, LocalDate.now(), DocumentType.valueOf("BOOK"));
//         documentRepository.save(document2);

//         Document document3 = new Document(null, "Une vie", "Simone Veil", Year.of(2007), true, LocalDate.now(), DocumentType.valueOf("BOOK"));
//         documentRepository.save(document3);

//         Document document4 = new Document(null, "Bel–Ami", "Guy de Maupassant", Year.of(1885), true, LocalDate.now(), DocumentType.valueOf("BOOK"));
//         documentRepository.save(document4);

//         Document document5 = new Document(null, "Astérix", "Ren\u00E9 Goscinny", Year.of(1959), true, LocalDate.now(), DocumentType.valueOf("COMIC"));
//         documentRepository.save(document5);

//         Document document6 = new Document(null, "Les Aventures de Tintin", "Hergé", Year.of(1929), true, LocalDate.now(), DocumentType.valueOf("COMIC"));
//         documentRepository.save(document6);

//         Document document7 = new Document(null, "L’Appel de la forêt", "Jack London", Year.of(1903), true, LocalDate.now(), DocumentType.valueOf("BOOK"));
//         documentRepository.save(document7);

//         Document document8 = new Document(null, "Des fleurs pour Algernon", "Daniel Keyes", Year.of(1959), true, LocalDate.now(), DocumentType.valueOf("BOOK"));
//         documentRepository.save(document8);

//         Document document9 = new Document(null, "Archives de l'Opéra de Paris", "Opéra national de Paris", Year.of(1980), true, LocalDate.now(), DocumentType.valueOf("ARCHIVE"));
//         documentRepository.save(document9);

//         Document document10 = new Document(null, "Archives", "Archive", Year.of(1960), true, LocalDate.now(), DocumentType.valueOf("ARCHIVE"));
//         documentRepository.save(document10);

//         Document document11 = new Document(null, "Le Petit Prince", "Antoine de Saint-Exupéry", Year.of(1943), true, LocalDate.now(), DocumentType.valueOf("NOVEL"));
//         documentRepository.save(document11);

//         Member member1 = new Member(null, "Marie", "Dubois", "10 rue de la paix", "31000", "Toulouse", LocalDate.of(2022, 9, 15));
//         memberRepository.save(member1);

//         Member member2 = new Member(null, "Emma", "Lacroix", "1 boulevard de la liberté", "33000", "Bordeaux", LocalDate.of(2022, 11, 28));
//         memberRepository.save(member2);

//         Member member3 = new Member(null, "Jean", "Joshi", "1 rue saint denis", "75000", "Paris", LocalDate.of(2023, 1, 7));
//         memberRepository.save(member3);

//         Member member4 = new Member(null, "Hugo", "Duchamp", "1 avenue jean jaures", "33000", "Bordeaux", LocalDate.of(2023, 1, 28));
//         memberRepository.save(member4);

//         Member member5 = new Member(null, "Clara", "Jamil", "1 avenue de la république", "33800", "Bordeaux", LocalDate.of(2023, 3, 17));
//         memberRepository.save(member5);

//         Member member6 = new Member(null, "Benoit", "Martin", "1 rue du commerce", "33000", "Bordeaux", LocalDate.of(2023, 5, 11));
//         memberRepository.save(member6);

//         Borrow borrow1 = new Borrow();
//         borrow1.setMember(member2);
//         borrow1.setDocument(document1);
//         borrow1.setLoanDate(LocalDate.of(2023, 6, 21));
//         borrow1.setReturnDate(LocalDate.of(2023, 6, 30));
//         borrowRepository.save(borrow1);

//         Borrow borrow2 = new Borrow();
//         borrow2.setMember(member1);
//         borrow2.setDocument(document2);
//         document2.setAvailable(false);
//         borrow2.setLoanDate(LocalDate.of(2023, 7, 10));
//         documentRepository.save(document2);
//         borrowRepository.save(borrow2);

//         Borrow borrow3 = new Borrow();
//         borrow3.setMember(member1);
//         borrow3.setDocument(document3);
//         document3.setAvailable(false);
//         borrow3.setLoanDate(LocalDate.of(2023, 7, 11));
//         documentRepository.save(document3);
//         borrowRepository.save(borrow3);
//     }
// }
