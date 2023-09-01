package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.service.member.memberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class memberController {

    private final memberService memberService;


//    @PostMapping("/signup")
//    public ResponseEntity<Long> createMember(@RequestBody MemberCreateRequest request) {
//        Long result = memberService.createMember(request);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(result);
//    }
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
