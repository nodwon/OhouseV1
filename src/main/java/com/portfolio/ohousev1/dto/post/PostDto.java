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
        String img_path,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static  PostDto of(MemberDto memberDto,String title,String content){
        return  new PostDto(null, memberDto, title, content, null, null,null);
    }
    public static  PostDto of(MemberDto memberDto,String title,String content, String img_path, LocalDateTime createdAt, LocalDateTime modifiedAt){
        return  new PostDto(null, memberDto, title, content, img_path, createdAt,modifiedAt);
    }

    public  static PostDto from(Post entity){
        return new PostDto(
                entity.getId(),
                MemberDto.from(entity.getMember()),
                entity.getTitle(),
                entity.getContent(),
                entity.getImgPath(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
    public Post toEntity(Member member){
        return Post.of(
                member,
                title,
                content
        );
    }
}
