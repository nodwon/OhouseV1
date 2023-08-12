package com.portfolio.ohousev1.member;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Setter
    private String email;

    @NotNull
    @Setter
    private String password;

    @Builder
    public Member(Long id, String email, String password){
        this.id =id;
        this.email =email;
        this.password =password;
    }


}
