//package com.codestates.BocamDogam.image.service;
//
//import com.codestates.BocamDogam.image.entity.Image;
//import com.codestates.BocamDogam.image.repository.ImageRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ImageServiceImpl implements ImageService {
//    private final ImageRepository imageRepository;
//
//    public ImageServiceImpl(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }
//
//    @Override
//    public Image saveImage(Image image) {
//        return imageRepository.save(image);
//    }
//
//    @Override
//    public Optional<Image> findImage(Long imageId) {
//        return imageRepository.findById(imageId);
//    }
//
//    @Override
//    public void deleteImage(Long imageId) {
//        imageRepository.deleteById(imageId);
//    }
//}
