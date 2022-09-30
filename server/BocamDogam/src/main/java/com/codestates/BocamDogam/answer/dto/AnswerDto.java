package com.codestates.BocamDogam.answer.dto;

import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class AnswerDto {
    @Getter
    @Setter
    public static class Post {
        private String content;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long answerId;
        private String content;
    }

    @Getter
    @Setter
    public static class Response {
        private Long answerId;
        private Question question;
        private Member member;
        private String content;
        private int like_count;
        private boolean reported;
        private boolean block;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

    }
}
