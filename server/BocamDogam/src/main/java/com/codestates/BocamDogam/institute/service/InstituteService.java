package com.codestates.BocamDogam.institute.service;

import com.codestates.BocamDogam.institute.entity.Institute;
import org.springframework.data.domain.Page;

public interface InstituteService {
    // 교육기관 등록
    Institute createInstitute(Institute institute);

    // 단일 교육기관 정보 조회
    Institute findInstitute(Long instituteId);

    // 모든 교육기관 조회
    Page<Institute> findInstitutes(int page, int size);

    // 교육기관 정보 수정
    // Member updateMember(Institute institute);

    // 교육기관 삭제
    void deleteMember(Long instituteId);

    // 교육기관 검증
    Institute findVerifiedInstitute(Long InstituteId);

    // 교육기관 이름으로 검증
    void verifyExistName(String name);
}
