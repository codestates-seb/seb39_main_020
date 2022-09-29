package com.codestates.BocamDogam.course.service;

import com.codestates.BocamDogam.course.entity.Course;
import com.codestates.BocamDogam.course.repository.CourseRepository;
import com.codestates.BocamDogam.member.entity.Member;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    // 교육기관의 교육과정 생성
    Course createCourse(Course course);

    // 교육기관의 교육과정 조회
    Course findCourse(Long courseId);

    // 교육기관의 교육과정 전체 조회
    List<Course> findAllCourse();

    // 교육기관의 교육과정 정보 수정
    Course updateCourse(Course course);

    // 교육기관의 교육과정 삭제
    void deleteCourse(Long courseId);

    // 코스가 제대로 입력되는지 검증
    void verifyCourse(Course course);

    // 코스 검증
    Course findVerifiedCourse(Long courseId);

    // 등록 코스 검증
    void verifyExistCourseName(String name);
}
