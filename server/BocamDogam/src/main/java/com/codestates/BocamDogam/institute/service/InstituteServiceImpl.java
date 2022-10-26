package com.codestates.BocamDogam.institute.service;

import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.institute.repository.InstituteRepository;
import com.codestates.BocamDogam.review.entity.Review;
import com.codestates.BocamDogam.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {
    private final InstituteRepository instituteRepository;
    private final ReviewRepository reviewRepository;


    @Transactional
    @Override
    public Institute createInstitute(Institute institute) {
        verifyExistName(institute.getName());

        return instituteRepository.save(institute);
    }

    @Override
    public Institute findInstitute(Long instituteId) {

        return findVerifiedInstitute(instituteId);
    }

    @Override
    public Page<Institute> findInstitutes(int page, int size) {

        return instituteRepository.findAll(PageRequest.of(page, size,
                Sort.by("instituteId").descending()));
                // 소팅을 등록일 기준 최신순으로 해야할 수 있음
                // 사이즈의 수를 늘려서 한페이지에 보이도록
    }

    @Override
    public Institute updateInstitute(Institute institute) {
        Institute findInstitute = findVerifiedInstitute(institute.getInstituteId());

        Optional.ofNullable(institute.getName())
                .ifPresent(name -> findInstitute.setName(name));
        Optional.ofNullable(institute.getDescription())
                .ifPresent(description -> findInstitute.setDescription(description));
        Optional.ofNullable(institute.getHomepage())
                .ifPresent(homepage -> findInstitute.setHomepage(homepage));
        Optional.ofNullable(institute.getLocation())
                .ifPresent(location -> findInstitute.setLocation(location));

        return instituteRepository.save(findInstitute);
    }

    @Override
    public void deleteInstitute(Long instituteId) {
        Institute findInstitute =  findVerifiedInstitute(instituteId);
        instituteRepository.delete(findInstitute);
    }

    @Override
    public Institute findVerifiedInstitute(Long instituteId) {
        Optional<Institute> institute = instituteRepository.findById(instituteId);
        Institute findInstitute = institute.orElseThrow(() ->
            new BusinessLogicException(ExceptionCode.INSTITUTE_NOT_FOUND));

        return findInstitute;
    }

    @Override
    public void verifyExistName(String name) {
        Optional<Institute> verifyInstitute = instituteRepository.findByName(name);
        if(verifyInstitute.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.INSTITUTE_EXISTS);
        }
    }

    // 리뷰 점수를 바탕으로 평균 점수를 계산하는 메서드
    @Override
    public Double calculateInstituteAverageScore(Institute institute) {
        // 아이디로 매칭되는 리뷰 조회
        // 리뷰에 있는 평균 점수의 평균 구하기
        List<Review> reviews = institute.getReviewList();
        Double averageReviewScore = reviews.stream()
                .collect(Collectors.averagingDouble(Review::getAverageScore));

        return averageReviewScore;
    }
}
