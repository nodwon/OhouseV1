package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.dto.member.request.MemberUpdateRequest;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.constant.RoleType;
import com.portfolio.ohousev1.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<MemberDto> searchEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberDto::from);
    }

    @Transactional
    public MemberDto saveMember(String email, String Password, String name, String nickname, LocalDate birthday) {
        if(Password == null){
            throw new IllegalArgumentException("Password cannot be null");
        }
        String encodePassword = passwordEncoder.encode(Password);
        return MemberDto.from(
                memberRepository.save(Member.of(email, encodePassword, name, nickname, birthday))
        );
    }

    @Transactional
    public long updateMember(String email, MemberUpdateRequest dto) {

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 맴버 없습니다. id" + email));

        member.update(dto.getName(), dto.getNickname(), dto.getPassword());
        memberRepository.save(member);
        return member.getMemberNo();
    }

    public void deleteMember(String email) {
        memberRepository.deleteByEmail(email);
    }

    private void validate(Member member) { // 이메일 @확인
        String email = member.getEmail();
        String password = member.getPassword();
        if (!email.contains("@")) {
            throw new IllegalStateException("@포함되어 있지 않습니다.");
        }
        if (password.length() < 8) {
            throw new IllegalStateException("비밀번호가 8자이상이 아닙니다.");
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Could not found user" + email));
//
//        log.info("Success find member {}", member);
//        return User.builder()
//                .username(member.getEmail())
//                .password(passwordEncoder.encode(member.getPassword()))
//                .roles(RoleType.USER.getValue())
//                .build();
//    }

//    public String getNickname(String email){
//        Optional<String>
//    }
//    private void validate(String email, String password){ // 이메일 @확인
//        if(!email.contains("@")){
//            throw new IllegalStateException("@포함되어 있지 않습니다.");
//        }
//        if(password.length() <8){
//            throw new IllegalStateException("비밀번호가 8자이상이 아닙니다.");
//        }
//    }

}
