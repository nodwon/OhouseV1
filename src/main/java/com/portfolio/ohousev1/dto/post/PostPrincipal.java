package com.portfolio.ohousev1.dto.post;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.constant.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record PostPrincipal(
        Long MemberNo,
        String email,
        String Password,
        Collection<? extends GrantedAuthority> authorities,
        String name,
        String nickname,
        LocalDate birthday,
        Map<String, Object> oAuth2Attributes

) implements UserDetails, OAuth2User {

//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static PostPrincipal of(String email, String Password, Set<RoleType> roleTypes, String name, String nickname, LocalDate birthday) {
        return PostPrincipal.of(email, Password, roleTypes,name, nickname, birthday, Map.of());
    }

    public static PostPrincipal of(String email, String Password, Set<RoleType> roleTypes,String name, String nickname, LocalDate birthday, Map<String, Object> oAuth2Attributes) {
//        String encodePassword = passwordEncoder.encode(Password); // 비밀번호 해싱
        return new PostPrincipal(
                null,
                email,
                Password,
                roleTypes.stream()
                        .map(RoleType::getValue)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()),
                name,
                nickname,
                birthday,
                oAuth2Attributes
        );
    }

    public static PostPrincipal from(MemberDto dto) {
        return PostPrincipal.of(
                dto.email(),
                dto.Password(),
                dto.roleTypes(),
                dto.name(),
                dto.nickname(),
                dto.birthday()
        );
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2Attributes;
    }

    @Override
    public String getName() {
        return email;
    }

    public MemberDto toDto() {
        return MemberDto.of(
                email,
                Password,
                authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(RoleType::valueOf)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                name,
                nickname,
                birthday
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
