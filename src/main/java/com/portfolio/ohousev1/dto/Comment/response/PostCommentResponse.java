package com.portfolio.ohousev1.dto.Comment.response;

import com.portfolio.ohousev1.dto.Comment.PostCommentDto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public record PostCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String email,
        String nickname,
        String name,
        Long parentCommentId,
        Set<PostCommentResponse> childComments
) {
    public static PostCommentResponse of(Long id, String content, LocalDateTime createdAt,LocalDateTime modifiedAt, String email, String nickname, String name) {
        return PostCommentResponse.of(id, content, createdAt,modifiedAt, email, nickname, name, null);
    }
    public static PostCommentResponse of(Long id, String content, LocalDateTime createdAt,LocalDateTime modifiedAt, String email, String nickname, String name, Long parentCommentId) {
        Comparator<PostCommentResponse> childCommentCompare = Comparator
                .comparing(PostCommentResponse::createdAt)
                .thenComparingLong(PostCommentResponse::id);
        return new PostCommentResponse(id, content, createdAt, modifiedAt,email, nickname, name, parentCommentId,new TreeSet<>(childCommentCompare));
    }

    public static PostCommentResponse from(PostCommentDto dto){
        String nickname = dto.memberDto().nickname();
        if(nickname == null|| nickname.isBlank()){
            nickname = dto.memberDto().name();
        }
        return PostCommentResponse.of(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.modifiedAt(),
                dto.memberDto().email(),
                nickname,
                dto.memberDto().name(),
                dto.parentCommentId()
        );
    }
    public boolean hasParentComment() {
        return parentCommentId != null;
    }

}
