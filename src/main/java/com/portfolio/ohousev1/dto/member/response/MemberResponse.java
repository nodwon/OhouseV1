package com.portfolio.ohousev1.dto.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {

    private Long id;
    private String email;
    private String nickname;

}
