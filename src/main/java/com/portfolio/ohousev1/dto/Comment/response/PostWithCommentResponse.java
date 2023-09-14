package com.portfolio.ohousev1.dto.Comment.response;

import com.portfolio.ohousev1.dto.Comment.PostCommentDto;
import com.portfolio.ohousev1.dto.Comment.PostWithCommentDto;
import com.portfolio.ohousev1.entity.Post;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public record PostWithCommentResponse(
        Long post_no,
        String title,
        String content,
        LocalDateTime created_at,
        LocalDateTime modified_at,
        String email,
        String nickname,
        String name,
        Set<PostCommentResponse> postCommentResponse
) {

    public static PostWithCommentResponse of( Long post_no, String title, String content, LocalDateTime created_at, LocalDateTime modified_at, String email, String nickname, String name,Set<PostCommentResponse> postCommentResponse){
        return new PostWithCommentResponse(post_no, title, content, created_at, modified_at, email, nickname, name, postCommentResponse);
    }
    public  static PostWithCommentResponse from(PostWithCommentDto dto){
        String nickname = dto.memberDto().nickname();
        if(nickname == null|| nickname.isBlank()){
            nickname = dto.memberDto().name();
        }

        return  new PostWithCommentResponse(
                dto.post_no(),
                dto.title(),
                dto.content(),
                dto.createdAt(),
                dto.modifiedAt(),
                dto.memberDto().email(),
                nickname,
                dto.memberDto().name(),
                organizeChildComments(dto.postCommentDtos())
        );
    }

    private static Set<PostCommentResponse> organizeChildComments(Set<PostCommentDto> dtos){
        Map<Long, PostCommentResponse> map = dtos.stream()
                .map(PostCommentResponse::from)
                .collect(Collectors.toMap(PostCommentResponse::id, Function.identity()));
        map.values().stream()
                .filter(PostCommentResponse::hasParentComment)
                .forEach(comment -> {
                    PostCommentResponse parentComment = map.get(comment.parentCommentId());
                    parentComment.childComments().add(comment);
                });

        return map.values().stream()
                .filter(comment -> !comment.hasParentComment())
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator
                                .comparing(PostCommentResponse::createdAt)
                                .reversed()
                                .thenComparingLong(PostCommentResponse::id)
                        )
                ));
    }
}
