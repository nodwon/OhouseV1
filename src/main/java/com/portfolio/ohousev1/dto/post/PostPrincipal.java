package com.portfolio.ohousev1.dto.post;

import com.portfolio.ohousev1.dto.member.MemberDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record PostPrincipal(
        String email,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String name,
        String nickname,
        LocalDate Birthday
)implements UserDetails {
    public static PostPrincipal of(String email, String password, String name, String nickname, LocalDate Birthday){
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new PostPrincipal(
                email,
                password,
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()),
                name,
                nickname,
                Birthday
        );

    }
    public static PostPrincipal from(MemberDto dto){
        return PostPrincipal.of(
                dto.email(),
                dto.password(),
                dto.name(),
                dto.nickname(),
                dto.Birthday()
        );
    }
    public MemberDto toDto(){
        return MemberDto.of(
                email,
                password,
                name,
                nickname,
                Birthday
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
    public enum  RoleType{
        USER("ROLE_USER");

        @Getter
        private final String name;

        RoleType(String name){
            this.name =name;
        }
    }
}
