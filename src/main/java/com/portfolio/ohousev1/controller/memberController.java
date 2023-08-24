package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.member.request.MemberCreateRequest;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.service.member.memberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class memberController {

    private final memberService memberService;

//    @PostMapping("/api/v/members")
//    public String saveMember(@RequestBody @Valid MemberCreateRequest request){
//        Member member = new Member();
//        return  member.getEmail();
//    }
    // 업데이트
    // 삭제

    // 페이지
}
