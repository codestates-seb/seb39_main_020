package com.codestates.BocamDogam.institute.controller;

import com.codestates.BocamDogam.institute.dto.InstituteDto;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.institute.mapper.InstituteMapper;
import com.codestates.BocamDogam.institute.service.InstituteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/main/institutes")
public class InstituteController {
    private final InstituteService instituteService;
    private final InstituteMapper instituteMapper;

    public InstituteController(InstituteService instituteService, InstituteMapper instituteMapper) {
        this.instituteService = instituteService;
        this.instituteMapper = instituteMapper;
    }

    // 교육기관 생성 요청
    @PostMapping
    public ResponseEntity postInstitute(@Valid @RequestBody InstituteDto.Post requestBody) {
        Institute institute = instituteMapper.institutePostToInstitute(requestBody);
        Institute response = instituteService.createInstitute(institute);

        return new ResponseEntity<>(instituteMapper.instituteToInstituteResponse(response),
                HttpStatus.CREATED);
    }

    // 교육기관 조회 요청
    @GetMapping("/main/institutes/{institute-id}")
    public ResponseEntity getInstitute(@PathVariable("institute-id") @Positive Long instituteId) {
        Institute response = instituteService.findInstitute(instituteId);

        return new ResponseEntity<>(instituteMapper.instituteToInstituteResponse(response),
                HttpStatus.OK);
    }

    /**
     * TODO: MultiResponseDTO 활용하여 수정 예정
     * @param page
     * @param size
     * @return 
     */
    // 모든 교육기관 조회 요청
    @GetMapping("/main/institutes")
    public ResponseEntity getInstitutes(@Positive @RequestParam int page,
                                        @Positive @RequestParam int size) {
        Page<Institute> pageInstitute = instituteService.findInstitutes(page - 1, size);
        List<Institute> institutes = pageInstitute.getContent();

        return null;
    }

    // 교육기관 수정 요청
    @PatchMapping("/main/institutes/{institute-id}")
    public ResponseEntity patchInstitute(@PathVariable("institute-id") @Positive Long instituteId,
                                         @RequestBody InstituteDto.Patch requestBody) {
        requestBody.setInstituteId(instituteId);
        Institute institute = instituteService.updateInstitute(
                instituteMapper.institutePatchToInstitute(requestBody));

        return new ResponseEntity<>(instituteMapper.instituteToInstituteResponse(institute),
                HttpStatus.OK);
    }

    // 교육기관 삭제 요청
    @DeleteMapping("/main/institutes/{institute-id}")
    public ResponseEntity deleteInstitute(@PathVariable("institute-id") Long instituteId) {
        instituteService.deleteInstitute(instituteId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
