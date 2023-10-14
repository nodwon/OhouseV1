package com.portfolio.ohousev1.dto.post.request;


import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.post.PostDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PostsRequest(
        @NotEmpty(message = "제목은 필수 입력 사항입니다.")
        @Size(max = 200, message = "제목이 너무 길어요.")
        String title,
        @NotEmpty(message = "내용은 필수 입력 사항입니다.")
        String content
) {

    public static  PostsRequest of(String title, String content) {
        return new PostsRequest(title, content);
    }

    public PostDto toDto(MemberDto memberDto){
       return PostDto.of(
               memberDto,
               title,
               content
       );
    }

}
