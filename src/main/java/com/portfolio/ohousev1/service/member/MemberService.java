package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.constant.RoleType;
import com.portfolio.ohousev1.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<MemberDto> searchEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberDto::from);
    }
    public MemberDto getMember(String email) {
        return memberRepository.findById(email)
                .map(MemberDto::from)
                .orElseThrow(() -> new EntityNotFoundException("없는 회원입니다. - postId: " + email));
    }

    @Transactional
    public MemberDto saveMember(String email,String Password,  Set<RoleType> roleTypes, String name, String nickname, LocalDate birthday) {
        roleTypes = Set.of(RoleType.USER);
        validateDuplicateMember(email);
        String encodePassword = passwordEncoder.encode(Password); // 비밀번호 해싱
        return MemberDto.from(memberRepository.save(Member.of(email,encodePassword, roleTypes,name, nickname, birthday)));
    }


    private void validateDuplicateMember(String email) {

        // 이미 가입된 이메일 주소인지 확인
        Optional<Member> existingMember = memberRepository.findByEmail(email);
        if (existingMember.isPresent()) {
            // 이미 가입된 회원이면 예외를 발생
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Transactional
    public String updateMember(String email, MemberDto dto) {

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 맴버 없습니다. id" + email));
        Optional<Member> existingNickanme = memberRepository.findByNickname(dto.nickname());
        if (existingNickanme.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다. 다른 닉네임을 입력해주세요.");
        } else { // 닉네임만 바꿀때
            member.updateNickname(dto.nickname());
        }
        if (dto.Password() != null && !dto.Password().isEmpty()) { // 비밀번호만 바꿀때
            String encodePassword = passwordEncoder.encode(dto.Password()); // 비밀번호 해싱
            member.updatePassword(encodePassword);
        }else { // oauth2 추가 사항
            member.Additional(dto.name(), dto.nickname(), dto.birthday());
        }
        return dto.email();


    }

    public void deleteMember(String email) {
        memberRepository.deleteByEmail(email);
    }



}
