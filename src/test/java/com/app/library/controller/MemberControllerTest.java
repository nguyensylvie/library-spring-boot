package com.app.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.library.model.Member;
import com.app.library.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MemberService memberService;

    @Test
    public void testGetAllMembers() throws Exception {
        mockMvc.perform(get("/api/members"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    public void testGetMemberById() throws Exception {
        Long memberId = 1L;
        mockMvc.perform(get("/api/members/{memberId}", memberId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.memberId").value(memberId));
    }
    
    @Test
    public void testCreateMember() throws Exception {
        Member newMember = new Member();
        newMember.setFirstName("Prenom"); 
        newMember.setLastName("Nom"); 
        newMember.setAddress("1 rue de la paix");
        newMember.setPostalCode("33000");
        newMember.setCity("Bordeaux");
        newMember.setRegistrationDate(LocalDate.now());

        String jsonContent = objectMapper.writeValueAsString(newMember);
        
        mockMvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testUpdateMember() throws Exception {
        Optional<Member> existingMemberOptional = memberService.getMemberById(1L);
        if (existingMemberOptional.isPresent()) {
        	Member existingMember = existingMemberOptional.get();
            existingMember.setAddress("2 rue de la paix");

            String jsonContent = objectMapper.writeValueAsString(existingMember);

            mockMvc.perform(put("/api/members/{memberId}", existingMember.getMemberId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContent))
                    .andExpect(status().isOk());
        }
    }
    
    @Test
    public void testDeleteMember() throws Exception {
    	Optional<Member> memberToDeleteOptional = memberService.getMemberById(2L);

    	if (memberToDeleteOptional.isPresent()) {
    		Member memberToDelete = memberToDeleteOptional.get();
	         mockMvc.perform(delete("/api/members/{memberId}", memberToDelete.getMemberId()))
	                .andExpect(status().isOk());
    	}
    }
}


