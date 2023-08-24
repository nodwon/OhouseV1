package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URL;

//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private PasswordEncoder passwordEncoder;
//
//    private final MemberRepository memberRepository;
//
//    @Value("${kakao.rest-api-key}")
//    private String API_KEY;
//
//    @Value("${kakao.redirect-uri}")
//    private String REDIRECT_URI;
//
//    private String TOKEN_REQUEST_URL = "https://kauth.kakao.com/oauth/token";
//
//    private String USER_REQUEST_URL = "https://kapi.kakao.com/v2/user/me";
//    @Override
//    public MemberDto getKakao(String code) {
//
//
//        return null;
//    }
//}
