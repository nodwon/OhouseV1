package com.portfolio.ohousev1.dto.post;


import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;

import java.time.LocalDateTime;

public record PostDto(
        Long postId,
        MemberDto memberDto,
        String title,
        String content,
//        String img_path,
        Integer view,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static  PostDto of(MemberDto memberDto,String title,String content,Integer view){
        return  new PostDto(null, memberDto, title, content, view,null, null);
    }
    public static  PostDto of(MemberDto memberDto,String title,String content, Integer view, LocalDateTime createdAt, LocalDateTime modifiedAt){
        return  new PostDto(null, memberDto, title, content,view, createdAt,modifiedAt);
    }

    public  static PostDto from(Post entity){
        return new PostDto(
                entity.getId(),
                MemberDto.from(entity.getMember()),
                entity.getTitle(),
                entity.getContent(),
                entity.getView(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
    public Post toEntity(Member member){
        return Post.of(
                member,
                title,
                content,
                view
        );
    }
}
