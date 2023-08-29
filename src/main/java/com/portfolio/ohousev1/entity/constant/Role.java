package com.portfolio.ohousev1.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    ADMIN("ADMIN", "관리자"),
    USER("ROLE_USER", "일반 사용자");

    private final String value;
    private final String title;

}
