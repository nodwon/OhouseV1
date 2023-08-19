package com.portfolio.ohousev1.dto.post.request;

import com.portfolio.ohousev1.entity.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostsSaveRequestDto {
    private Integer postTypeNo;
    private Long memberNo;
    private Long productNo;
    private String title;
    private String content;

}
