package com.codestates.BocamDogam.member.dto;

import com.codestates.BocamDogam.member.entity.MemberRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class MemberResponseDto {
    private Long memberId;
    private String nickname;
    private String email;
    private MemberRoles roles;
    private boolean status;

    public String getRoles() {
        return roles.getRoles();
    }
}
