package com.codestates.BocamDogam.member.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class LoginDto {
    private String email;
    private String password;

}
