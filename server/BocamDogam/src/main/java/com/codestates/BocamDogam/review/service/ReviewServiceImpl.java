package com.codestates.BocamDogam.review.service;

import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.institute.service.InstituteService;
import com.codestates.BocamDogam.member.service.MemberService;
import com.codestates.BocamDogam.review.entity.Review;
import com.codestates.BocamDogam.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final InstituteService instituteService;


    @Override
    public Review createReview(Review review) {
        // 리뷰 작성 시 검증된 사람만 리뷰를 입력할 수 있도록 조정 필요
        // 멤버의 권한 정보를 받아 입력 폼에 들어오기 전에 확인 필요 -> 컨트롤러에서 확인
        Review savedReview = reviewRepository.save(review);
        savedReview.setAverageScore(calculateAverageScore(review));

        Institute findInstitute = instituteService.findInstitute(review.getInstitute().getInstituteId());
        findInstitute.setScore(instituteService.calculateInstituteAverageScore(findInstitute));

        return savedReview;
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
        Optional.of(review.getCurriculum())
                .ifPresent(curriculum -> findReview.setCurriculum(curriculum));
        Optional.of(review.getFresh())
                .ifPresent(fresh -> findReview.setFresh(fresh));
        Optional.of(review.getLecturer())
                .ifPresent(lecturer -> findReview.setLecturer(lecturer));
        Optional.of(review.getCare())
                .ifPresent(care -> findReview.setCare(care));
        Optional.of(review.getAtmosphere())
                .ifPresent(atmosphere -> findReview.setAtmosphere(atmosphere));
        Optional.ofNullable(review.getGood())
                .ifPresent(good -> findReview.setGood(good));
        Optional.ofNullable(review.getBad())
                .ifPresent(bad -> findReview.setBad(bad));
        Optional.ofNullable(review.getSummary())
                .ifPresent(summary -> findReview.setSummary(summary));

        findReview.setAverageScore(calculateAverageScore(review));

        Institute findInstitute = instituteService.findInstitute(review.getInstitute().getInstituteId());
        findInstitute.setScore(instituteService.calculateInstituteAverageScore(findInstitute));

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

    // 각 점수를 받아 평균 점수 산출
    public Double calculateAverageScore(Review review) {
        List<Integer> scoreList = new ArrayList<>();
        scoreList.add(review.getCurriculum());
        scoreList.add(review.getFresh());
        scoreList.add(review.getLecturer());
        scoreList.add(review.getCare());
        scoreList.add(review.getAtmosphere());

        Double averageScore = scoreList.stream()
                .collect(Collectors.averagingDouble(Integer::intValue));

        return averageScore;
    }
}
