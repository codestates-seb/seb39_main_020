package com.codestates.BocamDogam.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPatchDto {
    private Long memberId;
    private String nickname;
}
