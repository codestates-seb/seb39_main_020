package com.codestates.BocamDogam.course.controller;

import com.codestates.BocamDogam.course.mapper.CourseMapper;
import com.codestates.BocamDogam.course.service.CourseService;
import com.codestates.BocamDogam.institute.service.InstituteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main/institutes/{institute-id}/courses")
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final InstituteService instituteService;

    public CourseController(CourseService courseService,
                            CourseMapper courseMapper,
                            InstituteService instituteService) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.instituteService = instituteService;
    }

    // 특정 교육기관의 교육과정 생성 요청
    @PostMapping
    public ResponseEntity postCourse() {

        return new ResponseEntity<>();
    }

    // 특정 교육기관의 교육과정 조회 요청
    @GetMapping("/main/institutes/{institute-id}/courses/{course-id}")
    public ResponseEntity getCourse() {

        return new ResponseEntity<>();
    }

    // 해당 교육기관의 모든 교육 과정 조회 요청
    @GetMapping
    public ResponseEntity getCourses() {

        return new ResponseEntity<>();
    }

    // 특정 교육기관의 교육과정 정보 수정 요청
    @PatchMapping("/main/institutes/{institute-id}/courses/{course-id}")
    public ResponseEntity getCourses() {

        return new ResponseEntity<>();
    }

    // 특정 교육기관의 교육과정 삭제 요청
    @DeleteMapping("/main/institutes/{institute-id}/courses/{course-id}")
    public ResponseEntity deleteCourses() {


    }
}
