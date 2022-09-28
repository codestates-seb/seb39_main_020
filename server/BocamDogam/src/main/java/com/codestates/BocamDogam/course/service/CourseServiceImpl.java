package com.codestates.BocamDogam.course.service;

import com.codestates.BocamDogam.course.entity.Course;
import com.codestates.BocamDogam.course.repository.CourseRepository;
import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.institute.service.InstituteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final InstituteService instituteService;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(InstituteService instituteService,
                             CourseRepository courseRepository) {
        this.instituteService = instituteService;
        this.courseRepository = courseRepository;
    }

    // 교육과정 생성
    @Override
    public Course createCourse(Course course) {
        verifyCourse(course);
        Course savedCourse = courseRepository.save(course);

        return savedCourse;
    }

    // 교육과정 조회
    @Override
    public Course findCourse(Long courseId) {

        return findVerifiedCourse(courseId);
    }

    // 모든 교육과정 조회
    // 페이지네이션 필요한가?
    @Override
    public List<Course> findAllCourse() {

        return courseRepository.findAll();
    }

    // 교육과정 정보 수정
    @Override
    public Course updateCourse(Course course) {
        Course findCourse = findVerifiedCourse(course.getCourseId());
        Optional.of(course.getName())
                .ifPresent(name -> findCourse.setName(name));
        Optional.of(course.getDescription())
                .ifPresent(description -> findCourse.setDescription(description));
        Optional.of(course.getCategory())
                .ifPresent(category -> findCourse.setCategory(category));
        Optional.of(course.getLecturer())
                .ifPresent(lecturer -> findCourse.setLecturer(lecturer));
        Optional.of(course.isSupport())
                .ifPresent(support -> findCourse.setSupport(support));
        Optional.of(course.isRemote())
                .ifPresent(remote -> findCourse.setRemote(remote));
        Optional.of(course.getScale())
                .ifPresent(scale -> findCourse.setScale(scale));
        Optional.of(course.getCost())
                .ifPresent(cost -> findCourse.setCost(cost));
        Optional.of(course.getStartDate())
                .ifPresent(startDate -> findCourse.setStartDate(startDate));
        Optional.of(course.getEndDate())
                .ifPresent(endDate -> findCourse.setEndDate(endDate));
        Optional.of(course.getApplyStart())
                .ifPresent(applyStart -> findCourse.setApplyStart(applyStart));
        Optional.of(course.getApplyEnd())
                .ifPresent(applyEnd -> findCourse.setApplyEnd(applyEnd));

        // 기간 체크 필요
        // 모집 상태 체크 필요

        return courseRepository.save(findCourse);
    }

    // 교육과정 제거
    @Override
    public void deleteCourse(Long courseId) {
        Course course = findVerifiedCourse(courseId);
        courseRepository.delete(course);
    }

    // 코스 등록 전 검증
    @Override
    public void verifyCourse(Course course) {
        instituteService.findVerifiedInstitute(course.getInstitute().getInstituteId());
    }

    // 등록된 코스
    @Override
    public Course findVerifiedCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        Course findCourse = optionalCourse.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COURSE_NOT_FOUND));

        return findCourse;
    }

    // 등록된 코스인지 확인
    @Override
    public void verifyExistCourseName(String name) {
        Optional<Course> course = courseRepository.findByName(name);
        if(course.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COURSE_EXISTS);
        }
    }

    // 현재 모집 중인지 확인
    public LocalDateTime isApplying() {
        // 현재일 > 모집마감일 = 모집 종료
        // 현재일 <= 모집마감일 && 일수 차이 < 3 = 마감 임박
        // 현재일 <= 모집마감일 = 모집 중
        // 등록일, 마감일 없을 = 등록 예정

        return null;
    }

    // 전체 수업 일수 계산
    public LocalDateTime coursePeriod() {
        // 저장된 종료일, 시작일 불러오기
        // 페리오드 비트윈 함수를 사용하여 두 사이에 시간 구하기
        // 일로만 표시 =>

        return null;
    }



}
