package com.app.library.service;

import com.app.library.model.Member;
import com.app.library.repository.MemberRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BorrowService borrowService;

    @Autowired
    public MemberService(MemberRepository memberRepository, BorrowService borrowService) {
        this.memberRepository = memberRepository;
        this.borrowService = borrowService;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member createMember(Member member) {
        member.setRegistrationDate(LocalDate.now());
        return memberRepository.save(member);
    }

    public Member updateMember(Member updatedMember) {
        return memberRepository.save(updatedMember);
    }

    public void deleteMember(Long memberId) {
        borrowService.deleteBorrowsByMemberId(memberId);
        memberRepository.deleteById(memberId);
    }
}