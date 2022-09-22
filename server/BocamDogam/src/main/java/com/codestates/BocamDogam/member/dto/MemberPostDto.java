package com.codestates.BocamDogam.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class MemberPostDto {
    private String nickname;
    private String email;
    private String password;
}
