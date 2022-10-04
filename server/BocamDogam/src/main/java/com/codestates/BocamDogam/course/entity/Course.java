package com.codestates.BocamDogam.course.entity;

import com.codestates.BocamDogam.audit.BaseTimeEntity;
import com.codestates.BocamDogam.institute.entity.Institute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CourseCategory category = CourseCategory.ECT;

    @Column
    private String lecturer;

    @Column
    private LocalDateTime applyStart;

    @Column
    private LocalDateTime applyEnd;

    @Column
    private LocalDateTime StartDate;

    @Column
    private LocalDateTime EndDate;

    // 수강 기간
    // EndDate - StartDate
    @Column
    private int period;

    // 모집 인원 규모
    @Column
    private int scale;

    // 수강 비용
    // 단위는 원
    // 후불제이거나 연봉의 x% 같은 경우는?
    @Column
    private int cost;

    // 국비 지원 여부
    @Column
    private boolean support;

    // 원격 수업 여부
    @Column
    private boolean remote;

    // 모집 상태
    // 이넘 타입으로 변경이 필요한가?
    @Column
    private String ApplyStatus;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;
}
