package com.portfolio.ohousev1.dto.member.response;

import com.portfolio.ohousev1.dto.member.MemberDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberResponse(
        String email,
        String name,
        String nickname,
        LocalDate birthday,
        LocalDateTime modifiedAt
) {


    public static MemberResponse of(String email, String name, String nickname, LocalDate birthday, LocalDateTime modifiedAt) {
        return new MemberResponse(email,name, nickname,birthday, modifiedAt);
    }

    public static MemberResponse from(MemberDto dto){
        return new MemberResponse(
                dto.email(),
                dto.name(),
                dto.nickname(),
                dto.birthday(),
                dto.modifiedAt()
        );
    }
}
