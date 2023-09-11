package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.dto.post.request.PostsRequest;
import com.portfolio.ohousev1.dto.post.response.PostsResponse;
import com.portfolio.ohousev1.entity.constant.FormStatus;
import com.portfolio.ohousev1.entity.constant.SearchType;
import com.portfolio.ohousev1.service.PaginationService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
@Slf4j
public class PostApiController {
    private final PostService postService;

    private final PaginationService paginationService;

    @GetMapping("/myPage")
    public  String posts(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ){
        Page<PostsResponse> posts = postService.searchPosts(searchType,searchValue,pageable).map(PostsResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),posts.getTotalPages());
        map.addAttribute("Posts", posts);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        return "user/mypage";
    }


    //게시글 detail페이지
//    @GetMapping("/{postId}")
//    public String post(@PathVariable Long postId, ModelMap map){
//        PostsResponse postsResponse = PostsResponse.from(postService.getPost())
//        map.addAttribute("posts");
//    }

    //게시글 form 가져오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/form")
    public String postForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);
        return "posts/form";

    }

    //게시글 등록
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Long> newPost(@AuthenticationPrincipal PostPrincipal postPrincipal, @ModelAttribute PostsRequest request) {
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


    //게시글 업데이트
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{postId}/update")
    public ResponseEntity<Long> updatePost(@PathVariable Long postId, @AuthenticationPrincipal PostPrincipal postPrincipal,
                                           @ModelAttribute PostsRequest postsRequest) {

        Long result = postService.updatePost(postId, postsRequest.toDto(postPrincipal.toDto()));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(result);
    }

    //게시글 삭제
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @AuthenticationPrincipal PostPrincipal postPrincipal) {
        postService.deletePost(postId, postPrincipal.email());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    //게시글 페이지 조회
    // 게시글 조회


}
