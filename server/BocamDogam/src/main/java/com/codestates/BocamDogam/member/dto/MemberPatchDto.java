package com.codestates.BocamDogam.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPatchDto {
    private Long memberId;
    private String nickname;

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
