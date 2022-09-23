package com.codestates.BocamDogam.member.dto;

import com.codestates.BocamDogam.member.entity.MemberRoles;
import lombok.Getter;
import lombok.Setter;

public class MemberDto {
    @Getter
    @Setter
    public static class Post {
        private String nickname;
        private String email;
        private String password;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long memberId;
        private String nickname;

        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }
    }

    @Getter
    @Setter
    public static class Response {
        private Long memberId;
        private String nickname;
        private String email;
        private MemberRoles roles;
        private boolean status;

        public String getRoles() {
            return roles.getRoles();
        }
    }
}
