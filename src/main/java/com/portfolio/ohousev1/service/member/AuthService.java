package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.dto.member.MemberDto;

public interface AuthService {
    MemberDto getKakao(String code);
}
