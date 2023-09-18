package com.portfolio.ohousev1.dto.Comment;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(
        Long id,
        MemberDto memberDto,
        Long post_no,
        String content,
        Long parentCommentId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

        ) {
    public static  PostCommentDto of(Long post_no,MemberDto memberDto, String content){
        return PostCommentDto.of(post_no, memberDto, null,content );
    }

    public static PostCommentDto of(Long post_no, MemberDto memberDto, Long parentCommentId, String content) {
        return  PostCommentDto.of(null,memberDto,post_no,content,parentCommentId,null,null);
    }

    public static PostCommentDto of(Long id, //현재 여기가 문제임
                                    MemberDto memberDto,
                                    Long post_no,
                                    String content,
                                    Long parentCommentId,
                                    LocalDateTime createdAt,
                                    LocalDateTime modifiedAt) {
        return  new PostCommentDto(id,memberDto,post_no,content,parentCommentId,createdAt,modifiedAt);


    }
    public static  PostCommentDto from(PostComment entity){
        return  new PostCommentDto(
                entity.getId(),
                MemberDto.from(entity.getMember()),
                entity.getPost().getId(),
                entity.getContent(),
                entity.getParentCommentId(),
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
