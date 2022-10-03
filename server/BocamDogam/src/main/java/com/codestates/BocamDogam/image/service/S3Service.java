//package com.codestates.BocamDogam.image.service;
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.SdkClientException;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class S3Service {
//    private String bucket;
//    private final AmazonS3 amazonS3;
//
//    public String uploadImage(MultipartFile multipartFile) throws IOException {
//        String fileName = multipartFile.getOriginalFilename();
//
//        String ext = fileName.split("\\.")[1];
//        String contentType = "";
//
//        switch (ext) {
//            case "jpeg":
//                contentType = "image/jpeg";
//                break;
//            case "png":
//                contentType = "image/png";
//                break;
//        }
//
//        try {
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType(contentType);
//
//            amazonS3.putObject(new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//        }
//
//        catch(AmazonServiceException e) {
//            e.printStackTrace();
//        }
//
//        catch(SdkClientException e) {
//            e.printStackTrace();
//        }
//
//        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucket);
//        List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();
//
//        for(S3ObjectSummary object : objectSummaries) {
//            System.out.println("Object = " + object.toString());
//        }
//
//        return amazonS3.getUrl(bucket, fileName).toString();
//    }
//}
