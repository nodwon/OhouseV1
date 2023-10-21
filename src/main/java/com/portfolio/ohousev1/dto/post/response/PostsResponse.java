package com.portfolio.ohousev1.dto.post.response;

import com.portfolio.ohousev1.dto.post.PostDto;

import java.time.LocalDateTime;


public record PostsResponse(
        Long postNo,
        String title,
        String content,
        String nickname,
        String email,
        Integer view,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static PostsResponse of(Long id,String title, String content,String email,  String nickname,Integer view,LocalDateTime createdAt,LocalDateTime modifiedAt) {
        return new PostsResponse(id,title, content, nickname,email,view,createdAt, modifiedAt);
    }

    public static PostsResponse from(PostDto dto) {
        String nickname = dto.memberDto().nickname();

        return new PostsResponse(
                dto.postId(),
                dto.title(),
                dto.content(),
                nickname,
                dto.memberDto().toEntity().getEmail(),
                //                dto.img_path(),
                dto.view(),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }
}
