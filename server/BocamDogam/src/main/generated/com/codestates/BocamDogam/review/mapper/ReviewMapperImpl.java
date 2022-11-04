package com.codestates.BocamDogam.review.mapper;

import com.codestates.BocamDogam.review.dto.ReviewDto;
import com.codestates.BocamDogam.review.entity.Review;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T15:01:53+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review reviewPostToReview(ReviewDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Review review = new Review();

        review.setCurriculum( requestBody.getCurriculum() );
        review.setFresh( requestBody.getFresh() );
        review.setLecturer( requestBody.getLecturer() );
        review.setCare( requestBody.getCare() );
        review.setAtmosphere( requestBody.getAtmosphere() );
        review.setGood( requestBody.getGood() );
        review.setBad( requestBody.getBad() );
        review.setSummary( requestBody.getSummary() );

        return review;
    }

    @Override
    public Review reviewPatchToReview(ReviewDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Review review = new Review();

        review.setReviewId( requestBody.getReviewId() );
        review.setCurriculum( requestBody.getCurriculum() );
        review.setFresh( requestBody.getFresh() );
        review.setLecturer( requestBody.getLecturer() );
        review.setCare( requestBody.getCare() );
        review.setAtmosphere( requestBody.getAtmosphere() );
        review.setGood( requestBody.getGood() );
        review.setBad( requestBody.getBad() );
        review.setSummary( requestBody.getSummary() );

        return review;
    }

    @Override
    public ReviewDto.Response reviewToReviewResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto.Response response = new ReviewDto.Response();

        response.setReviewId( review.getReviewId() );
        response.setMember( review.getMember() );
        response.setInstitute( review.getInstitute() );
        response.setCurriculum( review.getCurriculum() );
        response.setFresh( review.getFresh() );
        response.setLecturer( review.getLecturer() );
        response.setCare( review.getCare() );
        response.setAtmosphere( review.getAtmosphere() );
        response.setAverageScore( review.getAverageScore() );
        response.setGood( review.getGood() );
        response.setBad( review.getBad() );
        response.setSummary( review.getSummary() );
        response.setLike_count( review.getLike_count() );
        response.setReported( review.isReported() );
        response.setBlock( review.isBlock() );

        return response;
    }

    @Override
    public List<ReviewDto.Response> reviewsToReviewResponses(List<Review> reviews) {
        if ( reviews == null ) {
            return null;
        }

        List<ReviewDto.Response> list = new ArrayList<ReviewDto.Response>( reviews.size() );
        for ( Review review : reviews ) {
            list.add( reviewToReviewResponse( review ) );
        }

        return list;
    }
}
