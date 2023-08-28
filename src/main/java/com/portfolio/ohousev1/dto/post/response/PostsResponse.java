package com.portfolio.ohousev1.dto.post.response;

import com.portfolio.ohousev1.dto.post.PostDto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record PostsResponse(
        Long postNo,
        Long memberNo,
        String title,
        String content,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static PostsResponse of(Long id, Long memberNo,String title, String content, LocalDateTime createdAt, String email, LocalDateTime modifiedAt) {
        return new PostsResponse(id, memberNo,title, content, email,createdAt, modifiedAt);
    }

    public static PostsResponse from(PostDto dto) {
        String nickname = dto.memberDto().nickname();

        return new PostsResponse(
                dto.postId(),
                dto.memberDto().member_no(),
                dto.title(),
                dto.content(),
                dto.img_path(),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }
}
