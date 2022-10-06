package com.codestates.BocamDogam.institute.service;

import com.codestates.BocamDogam.institute.entity.Institute;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

public interface InstituteService {
    // 교육기관 등록
    Institute createInstitute(Institute institute);

    // 단일 교육기관 정보 조회
    Institute findInstitute(Long instituteId);

    // 모든 교육기관 조회
    Page<Institute> findInstitutes(int page, int size);

    // 교육기관 정보 수정
    Institute updateInstitute(Institute institute);

    // 교육기관 삭제
    void deleteInstitute(Long instituteId);

    // 교육기관 검증
    Institute findVerifiedInstitute(Long InstituteId);

    // 교육기관 이름으로 검증
    void verifyExistName(String name);

    // 리뷰의 평균 평점을 합산하여 평균값 계산
    Double calculateInstituteAverageScore(Institute institute);
}
