package com.portfolio.ohousev1.dto.member.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.portfolio.ohousev1.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateRequest {
    @NotBlank
    @Email(message = "email 입력해주세요")
    private String email;
    @NotBlank(message = "비밀번호는 8자 이상입니다.")
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .nickname(this.nickname)
                .birthday(this.birthday)
                .build();
    }
}
