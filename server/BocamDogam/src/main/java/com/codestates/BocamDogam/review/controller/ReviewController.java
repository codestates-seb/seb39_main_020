package com.codestates.BocamDogam.review.controller;

import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.institute.service.InstituteService;
import com.codestates.BocamDogam.review.mapper.ReviewMapper;
import com.codestates.BocamDogam.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/main/institutes/{institute-id}/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final InstituteService instituteService;
    private final ReviewMapper reviewMapper;


    public ReviewController(ReviewService reviewService,
                            ReviewMapper reviewMapper,
                            InstituteService instituteService) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
        this.instituteService = instituteService;
    }


    // 리뷰를 생성하는 요청
    @PostMapping
    public ResponseEntity postReview() {
        return null;
    }

    // 리뷰를 조회하는 요청
    @GetMapping("/{review-id}")
    public ResponseEntity getReview() {
        return null;
    }

    // 모든 리뷰를 조회하는 요청
    @GetMapping
    public ResponseEntity postReviews() {
        return null;
    }

    // 리뷰를 수정하는 요청
    // 리뷰의 수정은 어드민으로 받아야 할듯 함 -> 나중에 추가
    @PatchMapping("/{review-id}")
    public ResponseEntity patchReview() {
        return null;
    }

    // 리뷰를 삭제하는 요청
    // 리뷰 삭제는 권한이 필요
    @DeleteMapping("/{review-id}")
    public ResponseEntity deleteReview(@PathVariable("review-id") @Positive Long reviewId) {
        // 삭제 권한 확인 메서드 추가
        reviewService.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
