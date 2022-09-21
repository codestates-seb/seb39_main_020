package com.codestates.BocamDogam.member.entity;

import lombok.Getter;

public enum MemberRoles {
    USER("일반 유저"),
    OPERATOR("부트캠프 운영자"),
    ADMINISTER("관리자");

    @Getter
    private final String roles;

    MemberRoles(String roles) {
        this.roles = roles;
    }
}
