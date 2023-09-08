package com.portfolio.ohousev1.dto.member;

import com.portfolio.ohousev1.entity.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record MemberDto (
     String email,
     String Password,
     String name,
     String nickname,
     LocalDate birthday,
     LocalDateTime createdAt,
     LocalDateTime modifiedAt
)
{
    public  static MemberDto of(String email,String Password, String name, String nickname,LocalDate birthday){
        return new MemberDto(email,Password,name,nickname,birthday,null,null);
    }
    public  static MemberDto of(String email,String Password, String name, String nickname,LocalDate birthday, LocalDateTime createdAt, LocalDateTime modifiedAt){
        return new MemberDto(email,Password,name,nickname,birthday,createdAt,modifiedAt);
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
                Password,
                name,
                nickname,
                birthday
        );
    }

}
