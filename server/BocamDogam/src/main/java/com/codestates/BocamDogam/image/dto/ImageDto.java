package com.codestates.BocamDogam.image.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class ImageDto {
    @Getter
    @Setter
    public static class Post {
        private String fileName;
        private String fileUrl;
        private MultipartFile file;
    }

    @Getter
    @Setter
    public static class Response {
        private Long imageId;
        private String fileName;
        private String fileUrl;
        private MultipartFile file;
    }
}
