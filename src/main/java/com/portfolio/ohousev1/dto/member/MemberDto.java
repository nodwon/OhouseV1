package com.portfolio.ohousev1.dto.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;
}
