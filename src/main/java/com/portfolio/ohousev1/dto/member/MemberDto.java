package com.portfolio.ohousev1.dto.member;

import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.constant.RoleType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


public record MemberDto (
        String email,
        String Password,
        Set<RoleType> roleTypes,
        String name,
        String nickname,
        LocalDate birthday,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
)
{



    public  static MemberDto of(String email, String Password,Set<RoleType> roleTypes,String name, String nickname, LocalDate birthday){
        return new MemberDto(email,Password, roleTypes,name,nickname,birthday,null,null);
    }
    public  static MemberDto of(String email, String Password,Set<RoleType> roleTypes, String nickname){
        return new MemberDto(email,Password,roleTypes,null,nickname,null,null,null);
    }
//    public  static MemberDto of(Long memberNo,String email,String Password, Set<RoleType> roleTypes, String nickname){
//        return new MemberDto(memberNo,email,Password, roleTypes,null,nickname,null,null,null);
//    }
    public  static MemberDto of(String email,String Password,Set<RoleType> roleTypes, String name, String nickname,LocalDate birthday, LocalDateTime createdAt, LocalDateTime modifiedAt){
        return new MemberDto(email,Password,roleTypes,name,nickname,birthday,createdAt,modifiedAt);
    }
    public static MemberDto of(MemberDto memberDto ) {
        return new MemberDto(memberDto.email, memberDto.Password,memberDto.roleTypes, memberDto.name, memberDto.nickname, memberDto.birthday, memberDto.createdAt, memberDto.modifiedAt);
    }
    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getEmail(),
                entity.getPassword(),
                entity.getRoleTypes(),
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
                roleTypes,
                name,
                nickname,
                birthday
        );
    }

}
