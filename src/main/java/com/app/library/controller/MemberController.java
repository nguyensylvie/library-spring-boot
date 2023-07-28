package com.app.library.controller;

import com.app.library.model.Member;
import com.app.library.service.BorrowService;
import com.app.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final BorrowService borrowService;

    @Autowired
    public MemberController(MemberService memberService, BorrowService borrowService) {
        this.memberService = memberService;
        this.borrowService = borrowService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberId}")
    public Optional<Member> getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @PutMapping("/{memberId}")
    public Member updateMember(@PathVariable Long memberId, @RequestBody Member updatedMember) {
        updatedMember.setMemberId(memberId);
        return memberService.updateMember(updatedMember);
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        borrowService.deleteBorrowsByMemberId(memberId);
        memberService.deleteMember(memberId);
    }
}

