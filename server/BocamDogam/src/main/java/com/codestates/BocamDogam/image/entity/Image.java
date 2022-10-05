package com.codestates.BocamDogam.image.entity;

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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column
    private String fileName;

    @Column
    private String fileUrl;

    public Image(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "imageid =" + imageId +
                ", fileName ='" + fileName + '\'' +
                ", fileUrl ='" + fileUrl + '\'' +
                '}';
    }
}
