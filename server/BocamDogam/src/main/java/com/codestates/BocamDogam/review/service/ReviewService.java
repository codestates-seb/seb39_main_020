package com.codestates.BocamDogam.review.service;

import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.review.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {
    // 특정 교육기관의 리뷰 생성 메서드
    Review createReview(Review review);

    // 특정 교육기관의 리뷰 조회 메서드
    Review findReview(Long reviewId);

    // 특정 교육기관의 모든 리뷰 조회 메서드
    Page<Review> findReviews(int page, int size);

    // 특정 교육기관의 리뷰 수정 메서드
    Review updateReview(Review review);

    // 특정 교육기관의 리뷰 삭제 메서드
    void deleteReview(Long reviewId);

    // 작성된 리뷰 검증
    Review findVerifiedReview(Long reviewId);

    // 리뷰 작성을 위한 검증
    void verifyReview(Review review);


}
