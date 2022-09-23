package com.codestates.BocamDogam.institute.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main/institutes")
public class InstituteController {
    // 교육기관 생성 요청
    @PostMapping
    public ResponseEntity postInstitute() {
        return null;
    }

    // 교육기관 조회 요청
    @GetMapping("/main/institutes/{institute-id}")
    public ResponseEntity getInstitute() {
        return null;
    }

    // 모든 교육기관 조회 요청
    @GetMapping("/main/institutes")
    public ResponseEntity getInstitutes() {
        return null;
    }

    // 교육기관 수정 요청
    @PatchMapping("/main/institutes/{institute-id}")
    public ResponseEntity patchInstitute() {
        return null;
    }

    // 교육기관 삭제 요청
    @DeleteMapping("/main/institutes/{institute-id}")
    public ResponseEntity deleteInstitute() {
        return null;
    }
}
