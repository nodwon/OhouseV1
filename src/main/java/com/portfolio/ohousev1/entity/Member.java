package com.portfolio.ohousev1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;

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

    public static Member of(String email, String password, String name, String nickname, LocalDate birthday) {
        return new Member(null,email,password,name,nickname,birthday,null,null);
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

    public  static Member of(Long member_no, String email, String nickname, String password, String name, LocalDate birthday){
        return new Member(member_no, email, nickname,password,name,birthday,null,null);
    }
    public  static Member of(Long id, String email, String nickname, String password, String name, LocalDate birthday,List<Post> posts,List<Order> orders){
        return new Member(id, email, nickname,password,name,birthday,posts,orders);
    }
}
