package com.portfolio.ohousev1.repository;

import com.portfolio.ohousev1.entity.Member;
import com.portfolio.ohousev1.entity.PostComment;
import com.portfolio.ohousev1.entity.constant.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("JPA  테스트")
@SpringBootTest
class MemberRepositoryTest {


    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    MemberRepositoryTest(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Test
   public void save() throws Exception {
       //given
        String rowpassword = "asdf12345";
        String password = passwordEncoder.encode(rowpassword);
        Set<RoleType> roleTypeSet =  Set.of(RoleType.USER);
        Member setmember = memberRepository.save(Member.of("sns", password, roleTypeSet, "name", "nickname", LocalDate.now()));
       //when
        assertEquals("sns", memberRepository.findByEmail(setmember.getEmail())); // ID가 예상한 값과 일치하는지 확인
       //then
   }
}