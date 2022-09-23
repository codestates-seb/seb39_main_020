package com.codestates.BocamDogam.institute.service;

import com.codestates.BocamDogam.institute.entity.Institute;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class InstituteServiceImpl implements InstituteService {

    @Override
    public Institute createInstitute(Institute institute) {
        return null;
    }

    @Override
    public Institute findInstitute(Long instituteId) {
        return null;
    }

    @Override
    public Page<Institute> findInstitutes(int page, int size) {
        return null;
    }

    @Override
    public void deleteMember(Long instituteId) {

    }

    @Override
    public Institute findVerifiedInstitute(Long InstituteId) {
        return null;
    }

    @Override
    public void verifyExistName(String name) {

    }
}
