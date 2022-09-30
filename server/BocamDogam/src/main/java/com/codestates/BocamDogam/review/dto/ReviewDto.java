package com.codestates.BocamDogam.review.dto;

import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

public class ReviewDto {
    @Getter
    @Setter
    public static class Post {
        private int curriculum;
        private int fresh;
        private int lecturer;
        private int care;
        private int atmosphere;
        private String good;
        private String bad;
        private String summary;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long reviewId;
        private int curriculum;
        private int fresh;
        private int lecturer;
        private int care;
        private int atmosphere;
        private String good;
        private String bad;
        private String summary;
    }

    @Getter
    @Setter
    public static class Response {
        private Long reviewId;
        private Member member;
        private Institute institute;
        private int curriculum;
        private int fresh;
        private int lecturer;
        private int care;
        private int atmosphere;
        private String good;
        private String bad;
        private String summary;
        private int like_count;
        private boolean reported;
        private boolean block;
    }
}
