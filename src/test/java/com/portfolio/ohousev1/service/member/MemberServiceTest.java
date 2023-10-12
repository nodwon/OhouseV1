package com.portfolio.ohousev1.service.member;

import com.portfolio.ohousev1.dto.member.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;


class MemberServiceTest {

    @Test
    void searchEmail() {
    }

//    @Test
//    void saveMember() {
//        String hashedPassword = new BCryptPasswordEncoder().encode("12345678");
//        MemberDto.of(
//                "test@email.com",hashedPassword,"testname","nick",
//                LocalDate.now(),
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );
//    }

    @Test
    void updateMember() {
    }

    @Test
    void deleteMember() {
    }
}