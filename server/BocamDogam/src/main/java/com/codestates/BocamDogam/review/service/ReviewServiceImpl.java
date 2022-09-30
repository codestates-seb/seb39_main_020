package com.codestates.BocamDogam.review.service;

import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.institute.service.InstituteService;
import com.codestates.BocamDogam.member.service.MemberService;
import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.review.entity.Review;
import com.codestates.BocamDogam.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final InstituteService instituteService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             MemberService memberService,
                             InstituteService instituteService) {
        this.reviewRepository = reviewRepository;
        this.memberService = memberService;
        this.instituteService = instituteService;
    }

    @Override
    public Review createReview(Review review) {
        // 리뷰 작성 시 검증된 사람만 리뷰를 입력할 수 있도록 조정 필요
        // 멤버의 권한 정보를 받아 입력 폼에 들어오기 전에 확인 필요

        return reviewRepository.save(review);
    }

    @Override
    public Review findReview(Long reviewId) {

        return findVerifiedReview(reviewId);
    }

    @Override
    public Page<Review> findReviews(int page, int size) {

        return reviewRepository.findAll(PageRequest.of(page, size,
                Sort.by("reviewId").descending()));
                // 생성일 기준으로 해야하나?
    }

    @Override
    public Review updateReview(Review review) {
        Review findReview = findVerifiedReview(review.getReviewId());
        Optional.ofNullable(review.getCurriculum())
                .ifPresent(curriculum -> findReview.setCurriculum(curriculum));


        return reviewRepository.save(findReview);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review findReview = findVerifiedReview(reviewId);
        reviewRepository.delete(findReview);
    }

    @Override
    public Review findVerifiedReview(Long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        Review findreview = review.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));

        return findreview;
    }

    @Override
    public void verifyReview(Review review) {
        // 회원이 존재하는지 확인
        memberService.findVerifiedMember(review.getMember().getMemberId());

        // 교육기관이 존재하는지 확인
        instituteService.findVerifiedInstitute(review.getInstitute().getInstituteId());
    }
}
