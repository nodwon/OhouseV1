package com.portfolio.ohousev1.dto.post.response;

import com.portfolio.ohousev1.entity.Post;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    Long postNo;
    Long memberNo;
    Long productNo;
    String title;
    String content;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    @QueryProjection
    public PostsResponseDto(Post posts){
        this.postNo = posts.getId();
        this.memberNo = posts.getMember().getMemberNo();
        this.productNo = posts.getProductId().getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.createdAt = posts.getCreatedAt();
        this.modifiedAt = posts.getModifiedAt();
    }
}
