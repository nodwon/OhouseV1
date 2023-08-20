package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.entity.constant.FormStatus;
import com.portfolio.ohousev1.service.PaginationService;
import com.portfolio.ohousev1.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostApiController {
    private final PostService postService;

    private final PaginationService paginationService;

    @GetMapping("/form")
    public String articleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);
        return "posts/form";
    }
    //게시글 등록
    @PostMapping("/form/new")
    public String newPost(@RequestBody @Valid PostsSaveRequestDto dto) {
        postService.CreatePost(dto);
        return "redirect:/";
    }

    //게시글 업데이트
    @GetMapping("/posts/{id}")
    public String updatePost( PostsUpdateRequestDto dto, @PathVariable Long id) {

        postService.updatePost(id, dto);
        return "posts/form";
    }

    //게시글 삭제
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id, @PathVariable String email) {
        postService.deletePost(id, email);
        return "redirect:/";
    }


}
