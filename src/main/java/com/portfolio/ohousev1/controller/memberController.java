package com.portfolio.ohousev1.controller;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public ResponseEntity<MemberDto> createMember(@ModelAttribute MemberDto request) {
        MemberDto result = memberService.saveMember(request.email(),request.Password(),request.name(),request.nickname(),request.birthday());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }


//    @PostMapping("/signup")
//    public ResponseEntity<Long> saveMember(@RequestBody MemberCreateRequest request) {
//        Long result = memberService
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(result);
//    }

    // 업데이트
    // 삭제

    // 페이지
}
