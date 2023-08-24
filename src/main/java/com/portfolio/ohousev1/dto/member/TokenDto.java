package com.portfolio.ohousev1.dto.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {
    private String type;
    private String accessToken;
    private String refreshToken;

    public TokenDto(String accessToken, String refreshToken){
        this.type = "Bearer";
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
