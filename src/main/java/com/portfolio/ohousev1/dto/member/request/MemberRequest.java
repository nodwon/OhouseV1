package com.portfolio.ohousev1.dto.member.request;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.post.PostDto;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.entity.constant.RoleType;

import java.time.LocalDate;
import java.util.Set;

public record MemberRequest(
        Long MemberNo,
        String email,
        String Password,
        Set<RoleType> roletypes,
        String name,
        String nickname,
        LocalDate birthday

        ) {


    public static MemberRequest of(Long MemberNo,
                                    String email,
                                   String Password,
                                   Set<RoleType> roletypes,
                                   String name,
                                   String nickname,
                                   LocalDate birthday){
        return new MemberRequest(MemberNo,email, Password,roletypes, name, nickname,birthday);
    }

}
