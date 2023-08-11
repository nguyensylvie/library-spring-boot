package com.app.library.controller;

import com.app.library.model.Member;
import com.app.library.service.BorrowService;
import com.app.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Membre", description = "API de gestion des membres")
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

    @Operation(
    summary = "Récupère tous les membres",
    description = "Récupère tous les membres")
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @Operation(
    summary = "Récupère un membre par ID",
    description = "Obtenez un membre en spécifiant son identifiant.")
    @GetMapping("/{memberId}")
    public Optional<Member> getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

    @Operation(
    summary = "Ajoute un nouveau membre",
    description = "Ajoute un nouveau membre")
    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @Operation(
    summary = "Met à jour un membre existant",
    description = "Met à jour un membre existant par ID")
    @PutMapping("/{memberId}")
    public Member updateMember(@PathVariable Long memberId, @RequestBody Member updatedMember) {
        updatedMember.setMemberId(memberId);
        return memberService.updateMember(updatedMember);
    }

    @Operation(
    summary = "Supprime un membre",
    description = "Supprime un membre par ID")
    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        borrowService.deleteBorrowsByMemberId(memberId);
        memberService.deleteMember(memberId);
    }
}

