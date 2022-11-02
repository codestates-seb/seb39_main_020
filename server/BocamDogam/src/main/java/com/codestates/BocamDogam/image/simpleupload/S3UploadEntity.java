package com.codestates.BocamDogam.image.simpleupload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class S3UploadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column
    private String fileName;

    @Column
    private String uploadImageUrl;

    public S3UploadEntity(String fileName, String uploadImageUrl) {
        this.fileName = fileName;
        this.uploadImageUrl = uploadImageUrl;
    }
}
