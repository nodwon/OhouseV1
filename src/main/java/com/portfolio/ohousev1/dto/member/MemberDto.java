package com.portfolio.ohousev1.dto.member;

import com.portfolio.ohousev1.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record MemberDto (
     Long member_no,
     String email,
     String nickname,
     String password,
     String name,
     LocalDate Birthday,
     LocalDateTime CreatedAt,
     LocalDateTime modifiedAt
)
{
    public  static MemberDto of(Long member_no, String email, String nickname, String password, String name, LocalDate birthday){
        return new MemberDto(member_no, email, nickname,password,name,birthday,null,null);
    }
    public  static MemberDto of(Long id, String email, String nickname, String password, String name, LocalDate birthday, LocalDateTime createdAt, LocalDateTime modifiedAt){
        return new MemberDto(id, email, nickname,password,name,birthday,createdAt,modifiedAt);
    }
    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getMemberNo(),
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
