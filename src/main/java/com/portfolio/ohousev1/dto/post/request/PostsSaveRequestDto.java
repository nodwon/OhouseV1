package com.portfolio.ohousev1.dto.post.request;

import com.portfolio.ohousev1.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostsSaveRequestDto {
    @NotNull(message = "제목을 입력하세요")
    private String title;
    @NotNull(message = "본문을 입력하세요")
    private String content;

    private String email;
    private String imgPath;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

}
