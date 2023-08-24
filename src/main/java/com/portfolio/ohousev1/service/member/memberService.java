package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.dto.member.request.MemberCreateRequest;
import com.portfolio.ohousev1.dto.member.request.MemberUpdateRequest;
import com.portfolio.ohousev1.dto.post.request.PostsSaveRequestDto;
import com.portfolio.ohousev1.dto.post.request.PostsUpdateRequestDto;
import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.Post;
import com.portfolio.ohousev1.repository.MemberRepository;
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
        Member savemember = memberRepository.save(request.toEntity());
        validateEmail(savemember);
        validatePassword(savemember);
        return  savemember.getMemberNo();
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
    private void validateEmail(Member member){ // 이메일 @확인
        String email = member.getEmail();
        if(!email.contains("@")){
            throw new IllegalStateException("@포함되어 있지 않습니다.");
        }
    }
    private void validatePassword (Member member){ // 비밀번호 8자이상인지랑 b
        String password = member.getPassword();
        if(password.length() <8){
            throw new IllegalStateException("비밀번호가 8자이상이 아닙니다.");
        }
        else {
            String encodedPassword = passwordEncoder.encode(password);
            member.update(member.getName(), member.getNickname(), encodedPassword);
            memberRepository.save(member);
        }

    }
}
