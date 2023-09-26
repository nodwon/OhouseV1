package com.portfolio.ohousev1.auth;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{
    private final Map<String, Object> attributes; //OAuth2User.getAttributes();
    private final Map<String, Object> attributesResponse;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("kako_account");
        this.attributesResponse = (Map<String, Object>) attributes.get("profile");
    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return attributesResponse.get("id").toString();

    }

    @Override
    public String getProvider() {
        return  "kakao";
    }



    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    @Override
    public String getNickname() {
        return attributes.get("nickname").toString();    }

    @Override
    public String getBirthday() {
        return attributes.get("birthday").toString();
    }
    @Override
    public String getBirthdayYear() {
        return attributes.get("birthyear").toString();
    }
}
