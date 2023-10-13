package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.member.request.MemberRequest;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class memberController {

    private final MemberService memberService;

    @GetMapping("/logout")
    public String login() {
        log.info("logout controller");
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String createMember(@ModelAttribute MemberRequest request) {
          PostPrincipal.from(memberService.saveMember(request.email(),request.Password(), request.dto().roleTypes(),request.name(),request.nickname(),request.birthday()));
        return "redirect:/";

    }

    // 업데이트
    // 삭제

    // 페이지
}

