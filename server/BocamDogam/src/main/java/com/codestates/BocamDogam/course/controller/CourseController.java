package com.codestates.BocamDogam.course.controller;

import com.codestates.BocamDogam.course.dto.CourseDto;
import com.codestates.BocamDogam.course.entity.Course;
import com.codestates.BocamDogam.course.mapper.CourseMapper;
import com.codestates.BocamDogam.course.service.CourseService;
import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.institute.service.InstituteService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity postCourse(@PathVariable("institute-id") @Positive Long instituteId,
                                     @Valid @RequestBody CourseDto.Post coursePost) {
        Course course = courseService.createCourse(courseMapper.coursePostToCourse(coursePost));

        return new ResponseEntity<>(courseMapper.courseToCourseResponse(course),
                HttpStatus.CREATED);
    }

    // 특정 교육기관의 교육과정 조회 요청
    @GetMapping("/{course-id}")
    public ResponseEntity getCourse(@PathVariable("institute-id") @Positive Long instituteId,
                                    @PathVariable("course-id") @Positive Long courseId) {
        Institute institute = instituteService.findInstitute(instituteId);
        Course response = courseService.findCourse(courseId);

        return new ResponseEntity<>(courseMapper.courseToCourseResponse(response),
                HttpStatus.OK);
    }

    // 해당 교육기관의 모든 교육 과정 조회 요청
    @GetMapping
    public ResponseEntity getCourses(@PathVariable("institute-id") @Positive Long instituteId) {

        List<Course> result = courseService.findAllCourse();
        List<Course> response = result.stream()
                .filter(course -> course.getInstitute() == instituteService.findInstitute(instituteId))
                .collect(Collectors.toList());

        return new ResponseEntity<>(courseMapper.coursesToCourseResponse(response),
                HttpStatus.OK);
    }

    // 특정 교육기관의 교육과정 정보 수정 요청
    @PatchMapping("/{course-id}")
    public ResponseEntity getCourses(@PathVariable("institute-id") @Positive Long instituteId,
                                     @PathVariable("course-id") @Positive Long courseId,
                                     @Valid @RequestBody CourseDto.Patch coursePatch) {
        coursePatch.setCourseId(courseId);
        Course course = courseService.updateCourse(courseMapper.coursePatchToCourse(coursePatch));

        return new ResponseEntity<>(courseMapper.courseToCourseResponse(course),
                HttpStatus.OK);
    }

    // 특정 교육기관의 교육과정 삭제 요청
    @DeleteMapping("/{course-id}")
    public ResponseEntity deleteCourses(@PathVariable("institute-id") @Positive Long instituteId,
                                        @PathVariable("course-id") @Positive Long courseId) {
        instituteService.findInstitute(instituteId);
        courseService.deleteCourse(courseId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
