package com.portfolio.ohousev1.service.member;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



class MemberServiceTest {

    @Test
    void searchEmail() {
    }

    @Test
    void saveMember() {
        String hashedPassword = new BCryptPasswordEncoder().encode("12345678");
        System.out.println(hashedPassword);
    }

    @Test
    void updateMember() {
    }

    @Test
    void deleteMember() {
    }
}