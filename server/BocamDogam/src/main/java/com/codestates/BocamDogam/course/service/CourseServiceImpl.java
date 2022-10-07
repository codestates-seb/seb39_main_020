package com.codestates.BocamDogam.course.service;

import com.codestates.BocamDogam.course.entity.ApplyStatus;
import com.codestates.BocamDogam.course.entity.Course;
import com.codestates.BocamDogam.course.repository.CourseRepository;
import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.institute.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final InstituteService instituteService;
    private final CourseRepository courseRepository;

    // 교육과정 생성
    @Override
    public Course createCourse(Course course) {
        verifyCourse(course);
        Course savedCourse = courseRepository.save(course);
        isApplying(course);
        course.setPeriod(getCoursePeriod(course));

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
        Optional.ofNullable(course.getName())
                .ifPresent(name -> findCourse.setName(name));
        Optional.ofNullable(course.getDescription())
                .ifPresent(description -> findCourse.setDescription(description));
        Optional.ofNullable(course.getCategory())
                .ifPresent(category -> findCourse.setCategory(category));
        Optional.ofNullable(course.getLecturer())
                .ifPresent(lecturer -> findCourse.setLecturer(lecturer));
        Optional.ofNullable(course.isSupport())
                .ifPresent(support -> findCourse.setSupport(support));
        Optional.ofNullable(course.isRemote())
                .ifPresent(remote -> findCourse.setRemote(remote));
        Optional.ofNullable(course.getScale())
                .ifPresent(scale -> findCourse.setScale(scale));
        Optional.ofNullable(course.getCost())
                .ifPresent(cost -> findCourse.setCost(cost));
        Optional.ofNullable(course.getStartDate())
                .ifPresent(startDate -> findCourse.setStartDate(startDate));
        Optional.ofNullable(course.getEndDate())
                .ifPresent(endDate -> findCourse.setEndDate(endDate));
        Optional.ofNullable(course.getApplyStart())
                .ifPresent(applyStart -> findCourse.setApplyStart(applyStart));
        Optional.ofNullable(course.getApplyEnd())
                .ifPresent(applyEnd -> findCourse.setApplyEnd(applyEnd));

        isApplying(course);
        course.setPeriod(getCoursePeriod(course));

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
    public void isApplying(Course course) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startApplyDay = course.getApplyStart();
        LocalDateTime endApplyDay = course.getApplyEnd();
        /**
         * 코스를 입력받아서 진행
         * 현재일 < 모집시작일 = 모집 예정(디폴트)
         * 현재일 > 모집마감일 = 모집 종료
         * 현재일 > 모집시작일 && 현재일 <= 모집마감일 = 모집 중
         * 모집마감일 - 현재일 <= 7 = 마감임박
        */
        if(today.compareTo(startApplyDay) > 0 && today.compareTo(endApplyDay) <= 0) {
            course.setApplyStatus(ApplyStatus.APPLY_ACTIVE);
        }
        // 마감일 - 현재일 <= 7
        if(Math.abs(ChronoUnit.DAYS.between(today, endApplyDay)) <= 7) {
            course.setApplyStatus(ApplyStatus.APPLY_DEADLINE);
        }
        if(today.compareTo(endApplyDay) > 0) {
            course.setApplyStatus(ApplyStatus.APPLY_END);
        }
    }

    // 전체 수업 일수 계산
    public Long getCoursePeriod(Course course) {
        LocalDateTime startDate = course.getStartDate();
        LocalDateTime endDate = course.getEndDate();
        Long period = Math.abs(ChronoUnit.DAYS.between(startDate, endDate));

        return period;
    }
}
