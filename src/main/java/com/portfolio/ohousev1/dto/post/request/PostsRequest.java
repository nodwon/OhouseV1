package com.portfolio.ohousev1.dto.post.request;


import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.post.PostDto;

public record PostsRequest(
        String title,
        String content,
        String imgpath
) {

    public static  PostsRequest of(String title, String content, String imgpath) {
        return new PostsRequest(title, content, imgpath);
    }

    public PostDto toDto(MemberDto memberDto){
       return PostDto.of(
               memberDto,
               title,
               content,
               imgpath
       );
    }

}
