package com.codestates.BocamDogam.institute.controller;

import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.institute.dto.InstituteDto;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.institute.mapper.InstituteMapper;
import com.codestates.BocamDogam.institute.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main/institutes")
public class InstituteController {
    private final InstituteService instituteService;
    private final InstituteMapper instituteMapper;


    // 교육기관 생성 요청
    // 향후 별도의 입력폼이 생길 경우 유저의 권한(어드민)을 체크하여 입력하도록 수정
    @PostMapping
    public ResponseEntity postInstitute(@Valid @RequestBody InstituteDto.Post requestBody) {
        Institute institute = instituteMapper.institutePostToInstitute(requestBody);
        Institute response = instituteService.createInstitute(institute);

        return new ResponseEntity<>(instituteMapper.instituteToInstituteResponse(response),
                HttpStatus.CREATED);
    }

    // 교육기관 조회 요청
    @GetMapping("/{institute-id}")
    public ResponseEntity getInstitute(@PathVariable("institute-id") @Positive Long instituteId) {
        Institute response = instituteService.findInstitute(instituteId);

        return new ResponseEntity<>(instituteMapper.instituteToInstituteResponse(response),
                HttpStatus.OK);
    }

    // 모든 교육기관 조회 요청
    @GetMapping
    public ResponseEntity getInstitutes(@Positive @RequestParam int page,
                                        @Positive @RequestParam int size) {
        Page<Institute> pageInstitutes = instituteService.findInstitutes(page - 1, size);
        List<Institute> institutes = pageInstitutes.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(instituteMapper.institutesToInstituteResponses(institutes),
                        pageInstitutes), HttpStatus.OK);
    }

    // 교육기관 수정 요청
    // 향후 별도의 입력폼이 생길 경우 유저의 권한(어드민)을 체크하여 입력하도록 수정
    @PatchMapping("/{institute-id}")
    public ResponseEntity patchInstitute(@PathVariable("institute-id") @Positive Long instituteId,
                                         @RequestBody InstituteDto.Patch requestBody) {
        requestBody.setInstituteId(instituteId);
        Institute institute = instituteService.updateInstitute(
                instituteMapper.institutePatchToInstitute(requestBody));

        return new ResponseEntity<>(instituteMapper.instituteToInstituteResponse(institute),
                HttpStatus.OK);
    }

    // 교육기관 삭제 요청
    @DeleteMapping("/{institute-id}")
    public ResponseEntity deleteInstitute(@PathVariable("institute-id") Long instituteId) {
        instituteService.deleteInstitute(instituteId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
