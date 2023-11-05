package com.portfolio.ohousev1.dto.member.request;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.constant.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;


public record MemberRequest(
        @NotEmpty(message = "이메일 필수입니다.")
        @Email(message = "올바른 이메일 주소를 입력하세요.")
        String email,
        @NotEmpty(message = "비밀번호 필수입니다.")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "비밀번호는 최소 8자 이상이어야 하며, 대문자, 소문자, 숫자를 포함해야 합니다.")
        String Password,
        @NotEmpty(message = "이름 필수입니다.")
        String name,
        @NotEmpty(message = "닉네임 필수입니다.")
        String nickname,

        LocalDate birthday
) {


    public static MemberRequest of(String email,String Password, String name, String nickname, LocalDate birthday) {
        return new MemberRequest(email,Password,name,nickname,birthday);
    }
    public MemberDto dto(){
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return MemberDto.of(email,Password,roleTypes,name,nickname,birthday);
    }
}


