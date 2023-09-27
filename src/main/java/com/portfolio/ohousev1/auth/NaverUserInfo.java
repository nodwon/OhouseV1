package com.portfolio.ohousev1.auth;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{
    private final Map<String, Object> attributes; //OAuth2User.getAttributes();
    private final Map<String, Object> attributesResponse;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
        this.attributesResponse = (Map<String, Object>) attributes.get("response");
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
        return  "naver";
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
        return attributes.get("nickname").toString();
    }




}
