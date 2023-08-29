package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.dto.member.request.MemberCreateRequest;
import com.portfolio.ohousev1.dto.member.request.MemberUpdateRequest;
import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class memberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public memberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public long createMember(MemberCreateRequest request){
        Member member = memberRepository.save(request.toEntity());
        validate(member);
        member.update(passwordEncoder.encode(request.getPassword()));
        return member.getMemberNo();
    }
    @Transactional
    public long updateMember(String email, MemberUpdateRequest dto){

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 맴버 없습니다. id"+email));

        member.update(dto.getName(), dto.getNickname(), dto.getPassword());
        memberRepository.save(member);
        return member.getMemberNo();
    }
    public void deleteMember(String email){
        memberRepository.deleteByEmail(email);
    }
    private void validate(Member member){ // 이메일 @확인
        String email = member.getEmail();
        String password = member.getPassword();
        if(!email.contains("@")){
            throw new IllegalStateException("@포함되어 있지 않습니다.");
        }
        if(password.length() <8){
            throw new IllegalStateException("비밀번호가 8자이상이 아닙니다.");
        }
    }
}
