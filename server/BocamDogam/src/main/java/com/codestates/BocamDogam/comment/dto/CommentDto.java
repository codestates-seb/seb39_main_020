package com.codestates.BocamDogam.comment.dto;

import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @Setter
    public static class Post {
        private String content;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long commentId;
        private String content;
    }

    @Getter
    @Setter
    public static class Response {
        private Long commentId;
        private com.codestates.BocamDogam.post.entity.Post post;
        private Member member;
        private String content;
        private int like_count;
        private boolean reported;
        private boolean block;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

    }
}
