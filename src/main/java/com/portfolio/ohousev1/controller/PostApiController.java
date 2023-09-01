package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.dto.post.request.PostsRequest;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.dto.post.response.PostsResponse;
import com.portfolio.ohousev1.entity.constant.FormStatus;
import com.portfolio.ohousev1.service.PaginationService;
import com.portfolio.ohousev1.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final PaginationService paginationService;

//    @GetMapping("/postList")
//    public  String posts(
//            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable,
//            ModelMap map
//    ){
//        Page<PostsResponse> postsResponses = postService.searchArticles(pageable).map(PostsResponse::from);
//        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),posts.getTotalPages());
//        return "posts/index";
//    }

    //게시글 form 가져오기
    @GetMapping("/form")
    public String postForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);
        return "posts/form";

    }

    //게시글 등록
    @PostMapping
    public ResponseEntity<Long> newPost(@AuthenticationPrincipal PostPrincipal postPrincipal, @RequestBody PostsRequest request) {
        Long result = postService.savePost(request.toDto(postPrincipal.toDto()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    // 게시글 업데이트 form
    @GetMapping("form/{postId}")
    public String updatePostForm(@PathVariable Long postId, ModelMap map) {
        PostsResponse post = PostsResponse.from(postService.getPost(postId));
        map.addAttribute("post", post);
        map.addAttribute("formStatus", FormStatus.UPDATE);
        return "posts/form";
    }

    //게시글 업데이트
    @GetMapping("/{postId}")
    public ResponseEntity<Long> updatePost(PostsUpdateRequestDto request, @PathVariable Long postId) {

        Long result = postService.updatePost(postId, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(result);
    }

    //게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    //게시글 페이지 조회
    // 게시글 조회


}
