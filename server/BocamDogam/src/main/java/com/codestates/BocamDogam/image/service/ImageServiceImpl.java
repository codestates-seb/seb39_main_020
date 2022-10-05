package com.codestates.BocamDogam.image.service;

import com.codestates.BocamDogam.image.dto.ImageDto;
import com.codestates.BocamDogam.image.entity.Image;
import com.codestates.BocamDogam.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl {
    private final ImageRepository imageRepository;

    public void saveImage(ImageDto.Post postImage) {
        Image image = new Image(postImage.getFileName(), postImage.getFileUrl());
        imageRepository.save(image);
    }

    public List<Image> getImages() {
        List<Image> all = imageRepository.findAll();
        return all;
    }
}