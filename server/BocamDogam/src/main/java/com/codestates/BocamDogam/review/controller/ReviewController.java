package com.codestates.BocamDogam.review.controller;

import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.institute.service.InstituteService;
import com.codestates.BocamDogam.review.dto.ReviewDto;
import com.codestates.BocamDogam.review.entity.Review;
import com.codestates.BocamDogam.review.mapper.ReviewMapper;
import com.codestates.BocamDogam.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main/institutes/{institute-id}/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final InstituteService instituteService;
    private final ReviewMapper reviewMapper;


    // 리뷰를 생성하는 요청
    @PostMapping
    public ResponseEntity postReview(@RequestBody ReviewDto.Post reviewPostDto) {
        Review review = reviewService.createReview(reviewMapper.reviewPostToReview(reviewPostDto));

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponse(review),
                HttpStatus.CREATED);
    }

    // 리뷰를 조회하는 요청
    @GetMapping("/{review-id}")
    public ResponseEntity getReview(@PathVariable("review-id") @Positive Long reviewId) {
        Review review = reviewService.findReview(reviewId);

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponse(review),
                HttpStatus.OK);
    }

    // 모든 리뷰를 조회하는 요청
    @GetMapping
    public ResponseEntity postReviews(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {
        Page<Review> pageReviews = reviewService.findReviews(page - 1, size);
        List<Review> reviews = pageReviews.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(reviewMapper.reviewsToReviewResponses(reviews), pageReviews),
                HttpStatus.OK);
    }

    // 리뷰를 수정하는 요청
    // 리뷰의 수정은 어드민으로 받아야 할듯 함 -> 나중에 추가
    @PatchMapping("/{review-id}")
    public ResponseEntity patchReview(@PathVariable("review-id") @Positive Long reviewId,
                                      @Valid @RequestBody ReviewDto.Patch requestBody) {
        requestBody.setReviewId(reviewId);
        Review review = reviewService.updateReview(reviewMapper.reviewPatchToReview(requestBody));

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponse(review),
                HttpStatus.OK);
    }

    // 리뷰를 삭제하는 요청
    // 리뷰 삭제는 권한이 필요 -> 어드민에서만 가능 -> 나중에 추가
    @DeleteMapping("/{review-id}")
    public ResponseEntity deleteReview(@PathVariable("review-id") @Positive Long reviewId) {
        // 삭제 권한 확인 메서드 추가
        reviewService.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
