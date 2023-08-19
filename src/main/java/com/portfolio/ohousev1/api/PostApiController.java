package com.portfolio.ohousev1.api;

import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.service.PaginationService;
import com.portfolio.ohousev1.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/Posts")
@Controller
public class PostApiController {
    private final PostService postService;

    private final PaginationService paginationService;

    //게시글 등록
    @PostMapping("/form")
    public String NewPost(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        postService.CreatePost(postsSaveRequestDto);
        return "redirect:/";
    }

    //게시글 업데이트
    @GetMapping("/posts/{id}")
    public String updatePost(@PathVariable Long postId, PostsUpdateRequestDto dto) {

        postService.updatePost(postId, dto);
        return "posts/form";
    }

    //게시글 삭제
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long postId, @PathVariable String email) {
        postService.deletePost(postId, email);
        return "redirect:/";
    }

}
