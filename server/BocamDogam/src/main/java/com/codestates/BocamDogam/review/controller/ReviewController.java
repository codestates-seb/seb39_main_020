package com.codestates.BocamDogam.review.controller;

import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.institute.service.InstituteService;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.service.MemberService;
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
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main/institutes")
public class ReviewController {
    private final ReviewService reviewService;
    private final InstituteService instituteService;
    private final MemberService memberService;
    private final ReviewMapper reviewMapper;


    // 리뷰를 생성하는 요청
    // 리뷰 생성 시 유저 권한 확인
    @PostMapping("/{institute-id}/reviews")
    public ResponseEntity postReview(@PathVariable("institute-id") @Positive Long instituteId,
                                     @RequestHeader(value = "Authorization") String token,
                                     @RequestBody ReviewDto.Post requestBody) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] splitJwt = token.split("\\.");
        String payload = new String(decoder.decode(splitJwt[1]
                .replace("-", "+")
                .replace ("_", "/")));

        String email = new String(payload.substring(payload.indexOf("email") + 8, payload.indexOf("com")+3));
        Member member = memberService.findMemberByEmail(email);
        Institute institute = instituteService.findInstitute(instituteId);

        Review review = reviewService.createReview(reviewMapper.reviewPostToReview(requestBody));

        review.setMember(member);
        review.setInstitute(institute);

        Review response = reviewService.createReview(review);

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponse(response),
                HttpStatus.CREATED);
    }

    // 리뷰를 조회하는 요청
    @GetMapping("/{institute-id}/reviews/{review-id}")
    public ResponseEntity getReview(@PathVariable("review-id") @Positive Long reviewId) {
        Review review = reviewService.findReview(reviewId);

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponse(review),
                HttpStatus.OK);
    }

    // 모든 리뷰를 조회하는 요청
    @GetMapping("/{institute-id}/reviews")
    public ResponseEntity postReviews(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {
        Page<Review> pageReviews = reviewService.findReviews(page - 1, size);
        List<Review> reviews = pageReviews.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(reviewMapper.reviewsToReviewResponses(reviews), pageReviews),
                HttpStatus.OK);
    }

    // 리뷰를 수정하는 요청
    // 리뷰 수정은 작성자만 수정
    // 리뷰의 수정 인가는 어드민으로 받아야 할 듯 -> 나중에 추가
    @PatchMapping("/{institute-id}/reviews/{review-id}")
    public ResponseEntity patchReview(@PathVariable("institute-id") @Positive Long InstituteId,
                                      @PathVariable("review-id") @Positive Long reviewId,
                                      @RequestHeader("Authorization") String token,
                                      @Valid @RequestBody ReviewDto.Patch requestBody) {
        Long writerId = reviewService.findReview(reviewId).getReviewId();
        memberService.verifyWriterMember(token, writerId);

        requestBody.setReviewId(reviewId);
        Review response = reviewService.updateReview(reviewMapper.reviewPatchToReview(requestBody));

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponse(response),
                HttpStatus.OK);
    }

    // 리뷰를 삭제하는 요청
    // 리뷰 삭제는 권한이 필요 -> 어드민에서만 가능 -> 나중에 추가
    @DeleteMapping("/{institute-id}/reviews/{review-id}")
    public ResponseEntity deleteReview(@PathVariable("institute-id") @Positive Long InstituteId,
                                       @PathVariable("review-id") @Positive Long reviewId,
                                       @RequestHeader("Authorization") String token) {
        // 삭제 권한 확인 메서드 추가
        memberService.verifyWriterMember(token,
                reviewService.findVerifiedReview(reviewId).getMember().getMemberId());
        reviewService.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
