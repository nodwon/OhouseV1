package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.Comment.response.PostWithCommentResponse;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.dto.post.request.PostsRequest;
import com.portfolio.ohousev1.dto.post.response.PostsResponse;
import com.portfolio.ohousev1.entity.constant.FormStatus;
import com.portfolio.ohousev1.entity.constant.SearchType;
import com.portfolio.ohousev1.service.PaginationService;
import com.portfolio.ohousev1.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
@Slf4j
public class PostApiController {
    private final PostService postService;

    private final PaginationService paginationService;

    private static final Logger logger = LoggerFactory.getLogger(PostApiController.class);

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

    //게시글 detail페이지 with 댓글
    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, ModelMap map){
        PostWithCommentResponse posts = PostWithCommentResponse.from(postService.getPostWithComments(postId));
        map.addAttribute("post",posts);
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
    public ResponseEntity<Long> newPost(@AuthenticationPrincipal PostPrincipal postPrincipal, @RequestBody PostsRequest request) {
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
                                           @ModelAttribute PostsRequest postsRequest) {

        postService.updatePost(postId, postsRequest.toDto(postPrincipal.toDto()));

        return "fragments/main";
    }
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<String> deletePost(@PathVariable Long postId, @AuthenticationPrincipal PostPrincipal postPrincipal) {
        // 삭제 로직 수행
        postService.deletePost(postId, postPrincipal.email());

        // 삭제가 성공하면 204 No Content를 반환
        return ResponseEntity.noContent().build();
    }

    //게시글 페이지 조회
    // 게시글 조회


}
