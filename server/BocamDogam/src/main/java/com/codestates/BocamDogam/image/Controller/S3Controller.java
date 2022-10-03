//package com.codestates.BocamDogam.image.Controller;
//
//import com.codestates.BocamDogam.image.dto.ImageDto;
//import com.codestates.BocamDogam.image.entity.Image;
//import com.codestates.BocamDogam.image.mapper.ImageMapper;
//import com.codestates.BocamDogam.image.service.ImageService;
//import com.codestates.BocamDogam.image.service.S3Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/image/upload")
//public class S3Controller {
//    private final S3Service s3Service;
//    private final ImageService imageService;
//    private final ImageMapper imageMapper;
//
//    public S3Controller(S3Service s3Service,
//                        ImageService imageService,
//                        ImageMapper imageMapper) {
//        this.s3Service = s3Service;
//        this.imageService = imageService;
//        this.imageMapper = imageMapper;
//    }
//
//    @PostMapping
//    // 이미지 생성 요청
//    public String uploadImage(ImageDto.Post request) {
//        String url = s3Service.uploadImage(request.getFileName());
//        request.setFileUrl(url);
//        Image image = imageMapper.imagePostToImage(request);
//        imageService.saveImage(image);
//
//        return "rediect:/image/list";
//    }
//
//    // 이미지 조회
//    @GetMapping("/api/upload")
//    public String goToUpload() {
//        return "upload";
//    }
//
//    // 이미지
//    @GetMapping("/api/list")
//    public String listPage(Model model) {
//        List<FileEntity> fileList =fileService.getFiles();
//        model.addAttribute("fileList", fileList);
//        return "list";
//    }
//}
