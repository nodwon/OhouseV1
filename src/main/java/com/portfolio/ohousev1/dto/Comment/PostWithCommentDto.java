package com.portfolio.ohousev1.dto.Comment;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Post;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record PostWithCommentDto(
        Long id,
        MemberDto memberDto,
        Set<PostCommentDto> postCommentDtos,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static  PostWithCommentDto of(Long id, MemberDto memberDto, Set<PostCommentDto> postCommentDtos, String title, String content,LocalDateTime createdAt, LocalDateTime modifiedAt){
        return new PostWithCommentDto(id,memberDto,postCommentDtos,title,content,createdAt,modifiedAt);
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
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
