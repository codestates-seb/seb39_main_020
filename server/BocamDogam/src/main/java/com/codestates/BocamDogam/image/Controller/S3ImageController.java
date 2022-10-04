package com.codestates.BocamDogam.image.Controller;

import com.codestates.BocamDogam.image.service.S3ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image/upload")
public class S3ImageController {
    private final S3ImageService s3ImageService;

    public S3ImageController(S3ImageService s3ImageService) {
        this.s3ImageService = s3ImageService;
    }

    // 이미지 생성 요청
    @PostMapping
    public String uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {

        return s3ImageService.uploadImage(multipartFile);
    }

    // 이미지 조회

    // 이미지

}
