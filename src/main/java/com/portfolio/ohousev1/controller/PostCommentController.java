package com.portfolio.ohousev1.controller;

import com.portfolio.ohousev1.dto.Comment.request.PostCommentRequest;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.service.post.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class PostCommentController {
    private  final PostCommentService postCommentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new")
    public String postNewPostComment(@AuthenticationPrincipal PostPrincipal postPrincipal,PostCommentRequest postCommentRequest){
        postCommentService.savePostComment(postCommentRequest.toDto(postPrincipal.toDto()));
        return "redirect:/posts/" + postCommentRequest.post_no();
    }

    public String deletePostComment(@PathVariable Long commentId, @AuthenticationPrincipal PostPrincipal postPrincipal, Long post_no){
        postCommentService.deletePostComment(commentId, postPrincipal.getUsername());

        return "redirect:/posts/" + post_no;
    }

}
