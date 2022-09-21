package com.codestates.BocamDogam.member.dto;

import com.codestates.BocamDogam.member.entity.MemberRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String nickname;
    private String email;
    private MemberRoles roles;
    private boolean status;
}
