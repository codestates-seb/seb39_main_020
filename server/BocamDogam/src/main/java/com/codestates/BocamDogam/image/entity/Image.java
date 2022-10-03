//package com.codestates.BocamDogam.image.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Image {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long ImageId;
//
//    @Column
//    private String fileName;
//
//    @Column
//    private String fileUrl;
//
//    public Image(String fileName, String fileUrl) {
//        this.fileName = fileName;
//        this.fileUrl = fileUrl;
//    }
//
//    public String toString() {
//        return "Image{" +
//                "ImageId=" + ImageId +
//                ", fileName='" + fileName + '\'' +
//                ", fileUrl='" + fileUrl + '\'' +
//                '}';
//    }
//}
