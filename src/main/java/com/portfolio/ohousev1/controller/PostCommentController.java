package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.Comment.request.PostCommentRequest;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.service.post.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class PostCommentController {
    private  final PostCommentService postCommentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new")
    public String postNewPostComment(@AuthenticationPrincipal PostPrincipal postPrincipal, PostCommentRequest postCommentRequest, @PathVariable Long parent){
        postCommentService.savePostComment(postCommentRequest.toDto(postPrincipal.toDto()));
        return "redirect:/posts/" + postCommentRequest.post_no();
    }


//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/new")
//    public ResponseEntity<Long> postNewPostComment(@RequestBody PostCommentRequest postCommentRequest, @AuthenticationPrincipal PostPrincipal postPrincipal){
//        Long result = postCommentService.savePostComment(postCommentRequest.toDto(postPrincipal.toDto()));
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(result);
//    }


    @DeleteMapping("/{commentId}/delete")
    public String deletePostComment(@PathVariable Long commentId,
                                    @PathVariable Long postNo,
                                    @AuthenticationPrincipal PostPrincipal postPrincipal){
        postCommentService.deletePostComment(commentId, postPrincipal.getUsername());

        return "redirect:/posts/" + postNo;
    }

}
