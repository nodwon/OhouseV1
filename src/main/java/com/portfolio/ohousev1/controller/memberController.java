package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.member.request.MemberCreateRequest;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.service.member.memberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class memberController {

    private final memberService memberService;

//    @GetMapping("/members/new")
//    public String createForm(Model model){
//        model.addAttribute("memberForm", new MemberCreateRequest());
//        return "fragments/login";
//    }
//
//    @PostMapping("/member/new")
//    public String create(@RequestBody @Valid MemberCreateRequest request){
//        Member member = new Member(memberService.createMember(request));
//        return  member.getEmail();
//    }
    // 업데이트
    // 삭제

    // 페이지
}
