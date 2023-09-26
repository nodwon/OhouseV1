package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.auth.GoogleUserInfo;
import com.portfolio.ohousev1.auth.KakaoUserInfo;
import com.portfolio.ohousev1.auth.NaverUserInfo;
import com.portfolio.ohousev1.auth.OAuth2UserInfo;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Slf4j
public class OAuth2UserService extends DefaultOAuth2UserService {


    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public OAuth2UserService(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

        switch (provider) {
            case "google" -> oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
            case "naver" -> oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
            case "kakao" -> oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // nameAttributeKey
        String providerId = oAuth2UserInfo.getProviderId();
        String name = provider + "_" + providerId;
        String dummyPassword = "{bcrypt}" + UUID.randomUUID();
        String email = oAuth2UserInfo.getEmail();
        String nickname = oAuth2UserInfo.getNickname();
        String birthyear = oAuth2UserInfo.getBirthdayYear();
        String brithdaymon = oAuth2UserInfo.getBirthday();
        String brithdayAll = birthyear.concat(brithdaymon);
        LocalDate birthday = LocalDate.parse(brithdayAll, formatter);

        Member member = memberRepository.findMemberByEmail(email);

        if (member == null) {
            return memberService.searchEmail(email).map(PostPrincipal::from)
                    .orElseGet(() -> PostPrincipal.from(memberService.saveMember(
                            email,
                            dummyPassword,
                            name,
                            nickname,
                            birthday
                    )));
        } else return null;
    }

}
