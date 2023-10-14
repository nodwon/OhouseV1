package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.member.request.MemberRequest;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberRequest request) {
          MemberDto result =memberService.saveMember(request.email(),request.Password(), request.dto().roleTypes(),request.name(),request.nickname(),request.birthday());
          // 추가사항 - 소셜로그인이 email만 가져온것과 나머지 추가정보를 추가하기 위해 저장이 된상태에서 추가 기제 url로 보낸다.
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    // 업데이트
    // 삭제

    // 페이지
}

