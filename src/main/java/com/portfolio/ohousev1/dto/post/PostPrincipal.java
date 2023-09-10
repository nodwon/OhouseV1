package com.portfolio.ohousev1.dto.post;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.constant.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record PostPrincipal(
        String email,
        String Password,
        Collection<? extends GrantedAuthority> authorities,
        String name,
        String nickname,
        LocalDate birthday
)implements UserDetails {

    public static PostPrincipal of(String email, String Password, String name, String nickname, LocalDate birthday) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(Password); // 비밀번호 해싱
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new PostPrincipal(
                email,
                encodePassword,
                roleTypes.stream()
                        .map(RoleType::getValue)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()),
                name,
                nickname,
                birthday
        );
    }

    public static PostPrincipal from(MemberDto dto){
        return PostPrincipal.of(
                dto.email(),
                dto.Password(),
                dto.name(),
                dto.nickname(),
                dto.birthday()
        );
    }
    public MemberDto toDto(){
        return MemberDto.of(
                email,
                Password,
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
    private  static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

}
