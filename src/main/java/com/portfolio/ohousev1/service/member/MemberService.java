package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.dto.member.MemberDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<MemberDto> searchEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberDto::from);
    }

    @Transactional
    public MemberDto saveMember(String email, String Password, String name, String nickname, LocalDate birthday) {
        if (!email.contains("@")) {
            throw new IllegalStateException("@포함되어 있지 않습니다.");
        }
        if (Password.length() < 8) {
            throw new IllegalStateException("비밀번호가 8자이상이 아닙니다.");
        }
        return MemberDto.from(
                memberRepository.save(Member.of(email, Password, name, nickname, birthday))
        );
    }


//    @Transactional
//    public long updateMember(String email, MemberUpdateRequest dto) {
//
//        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 맴버 없습니다. id" + email));
//
//        member.update(dto.getName(), dto.getNickname(), dto.getPassword());
//        memberRepository.save(member);
//        return member.getMemberNo();
//    }

    public void deleteMember(String email) {
        memberRepository.deleteByEmail(email);
    }


}
