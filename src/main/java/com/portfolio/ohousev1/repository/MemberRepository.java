package com.portfolio.ohousev1.repository;

import com.portfolio.ohousev1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {


     Optional<Member> findByEmail(String email);
    Member findMemberByEmail(String email);
    //    Member findByNicknameAndEmail(String nickname, String email);
//
//    Member findByNickname(String nickname);
    void deleteByEmail(String email);

}

