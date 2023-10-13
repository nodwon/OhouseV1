package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.auth.GoogleUserInfo;
import com.portfolio.ohousev1.auth.KakaoUserInfo;
import com.portfolio.ohousev1.auth.NaverUserInfo;
import com.portfolio.ohousev1.auth.OAuth2UserInfo;
import com.portfolio.ohousev1.dto.post.PostPrincipal;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.constant.RoleType;
import com.portfolio.ohousev1.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.time.LocalTime.now;

@Service
@Slf4j
@Transactional(readOnly = true)
public class OAuth2UserService extends DefaultOAuth2UserService {


    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public OAuth2UserService(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

        switch (provider) {
            case "google" -> oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
            case "naver" -> oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
            case "kakao" -> oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        // nameAttributeKey
        String providerId = oAuth2UserInfo != null ? oAuth2UserInfo.getProviderId() : null;
        String name = provider + "_" + providerId;
        String dummyPassword = "{bcrypt}" + UUID.randomUUID();
        String email = oAuth2UserInfo != null ? oAuth2UserInfo.getEmail() : null;
        String nickname = oAuth2UserInfo.getNickname();
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        Optional<Member> byMember = memberRepository.findByEmail(email);
        if(byMember.isEmpty()){
          Member member = Member.builder()
                  .email(email)
                  .password(dummyPassword)
                  .roleTypes(roleTypes)
                  .name(name)
                  .nickname(nickname)
                  .birthday(null)
                  .build();
          memberRepository.flush();
          log.info("asdf"+ String.valueOf(member));
          memberRepository.save(member);
        }
//        return memberService.searchEmail(email).map(PostPrincipal::from)
//                .orElseGet(() -> PostPrincipal.from(memberService.saveMember(
//                        email,
//                        dummyPassword,
//                        roleTypes,
//                        name,
//                        nickname,
//                        null
//                )));
        return memberService.searchEmail(email).map(PostPrincipal::from)
                .orElseGet(() -> PostPrincipal.from(memberService.saveMember(
                        email,
                        dummyPassword,
                        roleTypes,
                        name,
                        nickname,
                        null
                )));
    }

}
