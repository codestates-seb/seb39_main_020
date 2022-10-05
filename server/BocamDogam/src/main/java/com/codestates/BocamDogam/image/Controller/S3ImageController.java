//package com.codestates.BocamDogam.image.Controller;
//
//import com.codestates.BocamDogam.image.service.S3ImageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.io.*;
//
//@RestController
//@RequestMapping("/image")
//@RequiredArgsConstructor
//public class S3ImageController {
//    private final S3ImageService s3ImageService;
//
//
//
//    @ApiOperation(value = "Amazon S3에 이미지 업로드", notes = "Amazon S3에 이미지 업로드 ")
//    @PostMapping("/image")
//    public ResponseEntity<List<String>> uploadImage(@ApiParam(value="img 파일들(여러 파일 업로드 가능)", required = true) @RequestPart List<MultipartFile> multipartFile) {
//        return ApiResponse.success(awsS3Service.uploadImage(multipartFile));
//    }
//
//    @ApiOperation(value = "Amazon S3에 업로드 된 파일을 삭제", notes = "Amazon S3에 업로드된 이미지 삭제")
//    @DeleteMapping("/image")
//    public ResponseEntity<Void> deleteImage(@ApiParam(value="img 파일 하나 삭제", required = true) @RequestParam String fileName) {
//        awsS3Service.deleteImage(fileName);
//        return ApiResponse.success(null);
//    }
//
//    // 이미지 조회
//
//    // 이미지
//
//}
