package com.codestates.BocamDogam.image.Controller;

import com.codestates.BocamDogam.image.dto.ImageDto;
import com.codestates.BocamDogam.image.entity.Image;
import com.codestates.BocamDogam.image.service.ImageServiceImpl;
import com.codestates.BocamDogam.image.service.S3ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class S3ImageController {
    private final S3ImageService s3ImageService;
    private final ImageServiceImpl imageServiceImpl;

    // 이미지 생성 요청
    @PostMapping("/upload")
    public String uploadImage(ImageDto.Post imageDto) throws IOException {
        String url = s3ImageService.uploadImage(imageDto.getFile());

        imageDto.setFileUrl(url);
        imageServiceImpl.saveImage(imageDto);

        return "redirect:/image";
    }

    @GetMapping
    public String goToUpload() {
        return "upload";
    }

    @GetMapping("/list")
    public String imagelistPage(Model model) {
        List<Image> imageList =imageServiceImpl.getImages();
        model.addAttribute("fileList", imageList);
        return "list";
    }

    // 이미지 조회

    // 이미지

}
