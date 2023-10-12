package com.portfolio.ohousev1.dto.member.response;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.post.response.PostsResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberResponse(
        String email,
        String Password,
        String name,
        String nickname,
        LocalDate birthday,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {


    public static MemberResponse of(String email, String Password, String name, String nickname, LocalDate birthday, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new MemberResponse(email,Password,name, nickname,birthday,createdAt, modifiedAt);
    }

    public static MemberResponse from(MemberDto dto){
        return new MemberResponse(
                dto.email(),
                dto.Password(),
                dto.name(),
                dto.nickname(),
                dto.birthday(),
                dto.createdAt(),
                dto.modifiedAt()
        );
    }
}
