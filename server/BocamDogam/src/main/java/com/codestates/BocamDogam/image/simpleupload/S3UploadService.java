package com.codestates.BocamDogam.image.simpleupload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3UploadService {
    private final AmazonS3Client amazonS3Client;
    private final S3UploadRepository s3UploadRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        // MultipartFile -> File
        File convertFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("file convert error"));
                // 파일을 변환할 수 없으면 에러

        // S3에 저장할 파일명
        String fileName = dirName + "/" + UUID.randomUUID() + "_" + convertFile.getName();

        // S3에 파일 업로드
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, convertFile).withCannedAcl(CannedAccessControlList.PublicRead));
        String uploadImageUrl = amazonS3Client.getUrl(bucket, fileName).toString();

        // DB에 저장
        S3UploadEntity s3UploadEntity = new S3UploadEntity(fileName, uploadImageUrl);
        s3UploadRepository.save(s3UploadEntity);

        // 로컬 파일 삭제
        convertFile.delete();

        return uploadImageUrl;
    }

    // S3 파일 삭제
    public void delete(String path) {
        amazonS3Client.deleteObject(bucket, path);
    }

    // 파일 convert 후 로컬에 업로드
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        // 바로 위에서 지정한 경로에 File이 생성됨(경로가 잘못되었다면 생성 불가능)
        if (convertFile.createNewFile()) {
            // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
