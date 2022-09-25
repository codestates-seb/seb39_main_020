package com.codestates.BocamDogam.institute.dto;

import lombok.Getter;
import lombok.Setter;

// DTO 클래스 축소를 위한 통합 DTO
public class InstituteDto {
    @Getter
    @Setter
    public static class Post {
        private String name;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long instituteId;
        private String name;
        private String description;
        private String homepage;
        private String location;
    }

    @Getter
    @Setter
    public static class Response {
        private Long instituteId;
        private String name;
        private String description;
        private String homepage;
        private String location;
        private Double score;
    }
}
