package com.portfolio.ohousev1.dto.member;

import com.portfolio.ohousev1.entity.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record MemberDto (
     String email,
     String nickname,
     String password,
     String name,
     LocalDate Birthday,
     LocalDateTime CreatedAt,
     LocalDateTime modifiedAt
)
{
    public  static MemberDto of(String email, String nickname, String password, String name,LocalDate Birthday){
        return new MemberDto(email, nickname,password,name,Birthday,null,null);
    }
    public  static MemberDto of( String email, String nickname, String password, String name, LocalDate birthday, LocalDateTime createdAt, LocalDateTime modifiedAt){
        return new MemberDto(email, nickname,password,name,birthday,createdAt,modifiedAt);
    }
    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getNickname(),
                entity.getBirthday(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public Member toEntity() {
        return Member.of(
                email,
                password,
                name,
                nickname,
                Birthday
        );
    }

}
