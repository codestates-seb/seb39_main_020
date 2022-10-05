package com.codestates.BocamDogam.image.simpleupload;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class S3UploadController {
    private final S3UploadService s3UploadService;

    @PostMapping("/upload")
    public String fileUpload(@RequestPart("files") MultipartFile multipartFile) throws IOException {
        s3UploadService.upload(multipartFile, "image"); // image 폴더에 파일 생성
        return "success";
    }

    @DeleteMapping("/upload")
    public String fileDelete(@RequestParam("path") String path) {
        s3UploadService.delete(path);
        return "success";
    }
}
