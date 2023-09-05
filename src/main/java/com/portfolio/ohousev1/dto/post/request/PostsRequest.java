package com.portfolio.ohousev1.dto.post.request;


import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.post.PostDto;

public record PostsRequest(
        String title,
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
