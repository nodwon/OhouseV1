package com.portfolio.ohousev1.dto.Comment;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(
        Long id,
        Long post_no,
        MemberDto memberDto,
        Long parentCommentId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

) {
    public static  PostCommentDto of(Long post_no, MemberDto memberDto, String content){
        return PostCommentDto.of(post_no, memberDto, null, content);
    }

    public static PostCommentDto of(Long post_no, MemberDto memberDto, Long parentCommentId, String content) {
        return  PostCommentDto.of(null, post_no, memberDto, parentCommentId, content, null,null);
    }

    public static PostCommentDto of(Long id, Long post_no, MemberDto memberDto, Long parentCommentId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return  new PostCommentDto(id, post_no, memberDto, parentCommentId, content,createdAt, modifiedAt);
    }

    public static  PostCommentDto from(PostComment entity){
        return  new PostCommentDto(
                entity.getId(),
                entity.getPost().getId(),
                MemberDto.from(entity.getMember()),
                entity.getParentCommentId(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public PostComment toEntity(Post post, Member member){
        return PostComment.of(
                post,
                member,
                content
        );
    }
}
