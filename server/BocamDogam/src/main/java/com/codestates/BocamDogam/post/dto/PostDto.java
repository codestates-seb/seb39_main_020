package com.codestates.BocamDogam.post.dto;

import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.post.entity.Post.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class PostDto {
    @Getter
    @Setter
    public static class Post {
        private String title;
        private String content;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long postId;
        private Board board;
        private String title;
        private String content;

    }

    @Getter
    @Setter
    public static class Response {
        private Long postId;
        private Member member;
        private Board board;
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
