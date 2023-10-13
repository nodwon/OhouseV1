package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.member.request.MemberRequest;
import com.portfolio.ohousev1.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class memberController {

    private final MemberService memberService;

    @GetMapping("/logout")
    public String login() {
        log.info("logout controller");
        return "redirect:/";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createMember(@ModelAttribute MemberRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }
//            String member = memberService.signupMember(request.email(),request.Password(),request.name(),request.nickname(),request.birthday());
            MemberDto member = memberService.saveMember(request.email(),request.Password(), request.dto().roleTypes(),request.name(),request.nickname(),request.birthday());

        return ResponseEntity.status(HttpStatus.CREATED).body(member);

    }

    // 업데이트
    // 삭제

    // 페이지
}

