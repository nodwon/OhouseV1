package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.Comment.response.PostWithCommentResponse;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.dto.post.request.PostsRequest;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateDto;
import com.portfolio.ohousev1.dto.post.response.PostsResponse;
import com.portfolio.ohousev1.entity.constant.FormStatus;
import com.portfolio.ohousev1.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
@Slf4j
public class PostApiController {
    private final PostService postService;

    //게시글 detail페이지 with 댓글
    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, ModelMap map) {
        PostWithCommentResponse posts = PostWithCommentResponse.from(postService.getPostWithComments(postId));
        map.addAttribute("post", posts);
        postService.updateView(postId);
        map.addAttribute("postComments", posts.postCommentResponse());
        map.addAttribute("totalCount", postService.getPostCount());
        return "posts/detail";
    }

    //게시글 form 가져오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/form")
    public String postForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);
        return "posts/form";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Long> newPost(@AuthenticationPrincipal PostPrincipal postPrincipal,@Valid @RequestBody PostsRequest request) {
        Long result = postService.savePost(request.toDto(postPrincipal.toDto()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    // 게시글 업데이트 form 가져오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{postId}/update")
    public String updatePostForm(@PathVariable Long postId, ModelMap map) {
        PostsResponse post = PostsResponse.from(postService.getPost(postId));
        map.addAttribute("post", post);
        map.addAttribute("formStatus", FormStatus.UPDATE);
        return "posts/updatePostForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{postId}/update")
    public String updatePost(@PathVariable Long postId, @AuthenticationPrincipal PostPrincipal postPrincipal,
                             @Valid @ModelAttribute PostsUpdateDto updateDto) {

        postService.updatePost(postId, updateDto.toDto(postPrincipal.toDto()));

        return "fragments/main";
    }

    @DeleteMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId, @AuthenticationPrincipal PostPrincipal postPrincipal) {
        // 삭제 로직 수행
        postService.deletePost(postId, postPrincipal.email());

        return "redirect:/";
    }


}
