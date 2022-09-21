package com.codestates.BocamDogam.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class MemberPostDto {
    private String nickname;
    @Email
    private String email;
    private String password;
}
