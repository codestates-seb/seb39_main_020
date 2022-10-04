package com.codestates.BocamDogam.review.mapper;

import com.codestates.BocamDogam.review.dto.ReviewDto;
import com.codestates.BocamDogam.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    Review reviewPostToReview(ReviewDto.Post requestBody);
    Review reviewPatchToReview(ReviewDto.Patch requestBody);
    ReviewDto.Response reviewToReviewResponse(Review review);
    List<ReviewDto.Response> reviewsToReviewResponses(List<Review> reviews);
}
