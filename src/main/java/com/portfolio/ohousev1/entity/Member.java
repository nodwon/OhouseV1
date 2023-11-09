package com.portfolio.ohousev1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.portfolio.ohousev1.entity.constant.RoleType;
import com.portfolio.ohousev1.entity.constant.RoleTypesConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member extends AuditingFields{

    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column
    private String Password;
    @Column
    private String name;
    @Column
    private String nickname;


    @Column
    private LocalDate birthday;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false)
    private Set<RoleType> roleTypes = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(String email, String password, String name, String nickname,
                  Set<RoleType> roleTypes,LocalDate birthday){
        this.email =email;
        this.Password =password;
        this.roleTypes =roleTypes;
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public static Member of(String email, String password, Set<RoleType> roleTypes,String name, String nickname, LocalDate birthday) {
        return new Member(email,password,name, nickname,birthday,null,roleTypes,null);
    }
    public  Member updatePassword(String password){
        this.Password = password;
        return this;
    }
   public String updateNickname(String name){
        this.nickname =name;
       return name;
   }
   public  Member Additional(String name, String nickname, LocalDate birthday){
        this.name =name;
        this.nickname = nickname;
        this.birthday = birthday;
        return  this;
   }
    public  static Member of( String email,String name, String nickname, String password, Set<RoleType> roleTypes,  LocalDate birthday){
        return new Member( email, name,nickname,password,birthday,null,roleTypes,null);
    }
    public  static Member of( String email,String name, String nickname, String password, Set<RoleType> roleTypes,  LocalDate birthday,List<Post> posts,List<Order> orders){
        return new Member(email,name, nickname,password,birthday,posts,roleTypes,orders);
    }
}
