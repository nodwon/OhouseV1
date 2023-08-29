package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.member.request.MemberCreateRequest;
import com.portfolio.ohousev1.dto.post.response.PostsResponse;
import com.portfolio.ohousev1.entity.constant.FormStatus;
import com.portfolio.ohousev1.service.member.memberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class memberController {

    private final memberService memberService;

    @GetMapping("/signup")
    public String createMember(ModelMap map) {
        map.addAttribute("memberform", FormStatus.CREATE);
        return "posts/login";
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> createMember(@RequestBody MemberCreateRequest request) {
        Long result = memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    // 업데이트
    // 삭제

    // 페이지
}
