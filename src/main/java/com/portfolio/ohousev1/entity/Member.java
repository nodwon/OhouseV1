package com.portfolio.ohousev1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.ohousev1.dto.member.request.MemberCreateRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member extends AuditingFields{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long MemberNo;

    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column
    private String Password;

    @Column
    private String nickname;

    @Column
    private String name;

    @Column
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String email, String password, String nickname,
                  String name,LocalDate birthday, String createBy){
        this.email =email;
        this.Password =password;
        this.nickname = nickname;
        this.name = name;
        this.birthday = birthday;
    }



    public  Member update(String name, String nickname, String password){
        this.name =name;
        this.nickname = nickname;
        this.Password = password;

        return this;
    }
   public void setNickname(String name){
        this.nickname =name;
   }
   public void setOauthAccessToken(String name){
        this.nickname = name;
   }

}
