package com.codestates.BocamDogam.review.service;

import com.codestates.BocamDogam.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public Review createReview(Review review) {
        return null;
    }

    @Override
    public Review findReview(Long instituteId) {
        return null;
    }

    @Override
    public Page<Review> findReviews(int page, int size) {
        return null;
    }

    @Override
    public Review updateReview(Review review) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {

    }

    @Override
    public void verifyExistName(String name) {

    }
}
