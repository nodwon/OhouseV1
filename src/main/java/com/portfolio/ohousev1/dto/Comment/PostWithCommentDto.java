package com.portfolio.ohousev1.dto.Comment;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Post;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record PostWithCommentDto(
        Long postId,
        MemberDto memberDto,
        Set<PostCommentDto> postCommentDtos,
        String title,
        String content,
        Integer view,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static  PostWithCommentDto of(Long postId, MemberDto memberDto, Set<PostCommentDto> postCommentDtos, String title, String content,Integer view,LocalDateTime createdAt, LocalDateTime modifiedAt){
        return new PostWithCommentDto(postId,memberDto,postCommentDtos,title,content,view,createdAt,modifiedAt);
    }
    public static PostWithCommentDto from(Post entity){
        return new PostWithCommentDto(
                entity.getId(),
                MemberDto.from(entity.getMember()),
                entity.getPostComments().stream()
                        .map(PostCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getView(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
