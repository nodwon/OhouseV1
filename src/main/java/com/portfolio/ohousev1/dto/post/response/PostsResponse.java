package com.portfolio.ohousev1.dto.post.response;

import com.portfolio.ohousev1.dto.post.PostDto;

import java.time.LocalDateTime;


public record PostsResponse(
        Long postNo,
        Long memberNo,
        String title,
        String content,
        String nickname,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static PostsResponse of(Long id, Long memberNo,String title, String content,  String nickname,LocalDateTime createdAt,LocalDateTime modifiedAt) {
        return new PostsResponse(id, memberNo,title, content, nickname,createdAt, modifiedAt);
    }

    public static PostsResponse from(PostDto dto) {
        String nickname = dto.memberDto().nickname();

        return new PostsResponse(
                dto.postId(),
                dto.memberDto().toEntity().getMemberNo(),
                dto.title(),
                dto.content(),
                nickname,
                //                dto.img_path(),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }
}
