package com.portfolio.ohousev1.dto.member.request;

public record MemberUpdateDto(
        String password,
        String nickname
) {
    public static MemberUpdateDto of(String password, String nickname) {
        return new MemberUpdateDto(password, nickname);
    }
    public static MemberUpdateDto of(String password) {
        return new MemberUpdateDto(password, null);
    }
}
