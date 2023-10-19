package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.member.request.MemberRequest;
import com.portfolio.ohousev1.dto.member.request.MemberUpdateRequest;
import com.portfolio.ohousev1.dto.member.response.MemberResponse;
import com.portfolio.ohousev1.dto.post.response.PostsResponse;
import com.portfolio.ohousev1.entity.constant.FormStatus;
import com.portfolio.ohousev1.entity.constant.SearchType;
import com.portfolio.ohousev1.service.PaginationService;
import com.portfolio.ohousev1.service.member.MemberService;
import com.portfolio.ohousev1.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;
    private final PaginationService paginationService;
    @GetMapping("/myPage")
    public String MyPage(
            Authentication authentication,
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ){
        Page<PostsResponse> posts = postService.searchPosts(searchType, searchValue, pageable).map(PostsResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), posts.getTotalPages());
        String email = authentication.getName();
        MemberResponse profile = MemberResponse.from(memberService.getMember(email)); // email을 사용하여 사용자 정보 검색
        map.addAttribute("info", profile);
        map.addAttribute("Posts", posts);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        return "user/mypage";
    }
    @GetMapping("/logout")
    public String login() {
        log.info("logout controller");
        return "redirect:/";
    }
    @PostMapping("/signup")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberRequest request) {
        MemberDto result = memberService.saveMember(request.email(), request.Password(), request.dto().roleTypes(), request.name(), request.nickname(), request.birthday());

                 return ResponseEntity.status(HttpStatus.CREATED)
                    .body(result);

    }
    // 업데이트
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit")
    public String UpdateMember(Authentication authentication, ModelMap map){
        String email = authentication.getName();
        MemberResponse Member = MemberResponse.from(memberService.getMember(email));
        map.addAttribute("member", Member);
        map.addAttribute("formStatus", FormStatus.UPDATE);
        return "user/additional";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit")
    public String UpdateMember(Authentication authentication, @ModelAttribute MemberUpdateRequest request) {
        String email = authentication.getName();
        memberService.updateMember(email,request.dto());
        return "redirect:/";

    }
    // 삭제
//            MemberDto additon= updatePost(result.email(),update).getBody();
//            return  ResponseEntity.status(HttpStatus.CREATED)
//                    .body(additon);
    // 페이지
}

