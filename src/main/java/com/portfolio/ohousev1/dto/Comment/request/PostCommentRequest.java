package com.portfolio.ohousev1.dto.Comment.request;

import com.portfolio.ohousev1.dto.Comment.PostCommentDto;
import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Post;

public record PostCommentRequest(
        Long post_no,
        Long parentCommentId,
        String content
) {
    public static PostCommentRequest of(Long post_no, String content){
        return PostCommentRequest.of(post_no,null, content);
    }
    public static PostCommentRequest of(Long post_no, Long parentCommentId, String content){
        return new PostCommentRequest(post_no,parentCommentId, content);
    }

    public PostCommentDto toDto(MemberDto memberDto){
        return PostCommentDto.of(
                post_no,
                memberDto,
                parentCommentId,
                content
        );
    }
}
