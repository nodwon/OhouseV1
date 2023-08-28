package com.portfolio.ohousev1.dto.post.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content =content;
    }

}
