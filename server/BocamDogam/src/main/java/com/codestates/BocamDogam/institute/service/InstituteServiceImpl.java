package com.codestates.BocamDogam.institute.service;

import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.institute.repository.InstituteRepository;
import com.codestates.BocamDogam.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstituteServiceImpl implements InstituteService {
    private final InstituteRepository instituteRepository;
    private final ReviewRepository reviewRepository;

    public InstituteServiceImpl(InstituteRepository instituteRepository,
                                ReviewRepository reviewRepository) {
        this.instituteRepository = instituteRepository;
        this.reviewRepository = reviewRepository;
    }

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
                Sort.by("instituteId").ascending()));
                // 소팅을 등록일 기준 최신순으로 해야할 수 있음
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
    public Double calculateAverageScore(Institute institute) {
        return null;
    }
}
