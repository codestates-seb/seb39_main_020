package com.codestates.BocamDogam.question.dto;

import com.codestates.BocamDogam.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class QuestionDto {

    @Getter
    @Setter
    public static class Post {
        private String title;
        private String content;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long questionId;
        private String title;
        private String content;
    }

    @Getter
    @Setter
    public static class Response {
        private Long questionId;
        private Member member;
        private String title;
        private String content;
        private int view;
        private int like_count;
        private boolean reported;
        private boolean block;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

    }
}
