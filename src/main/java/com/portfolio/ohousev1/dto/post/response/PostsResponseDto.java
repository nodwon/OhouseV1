package com.portfolio.ohousev1.dto.post.response;

import com.portfolio.ohousev1.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    Long postId;
    Integer postTypeNo;
    Long memberNo;
    Long productNo;
    String title;
    String content;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public PostsResponseDto(Post posts){
        this.postId = posts.getId();
        this.postTypeNo = posts.getPostTypeId().getPostTypeId();
        this.memberNo = posts.getMember().getMemberNo();
        this.productNo = posts.getProductId().getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.createdAt = posts.getCreatedAt();
        this.modifiedAt = posts.getModifiedAt();
    }
}
