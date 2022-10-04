package com.codestates.BocamDogam.institute.entity;

import com.codestates.BocamDogam.audit.BaseTimeEntity;
import com.codestates.BocamDogam.course.entity.Course;
import com.codestates.BocamDogam.review.entity.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Institute extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instituteId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    // 지역이 여러 군데에 있는 학원일 경우 배열이여야 할 수도 있음
    @Column
    private String location;

    @Column
    private String homepage;

    @OneToMany(mappedBy = "institute")
    private List<Course> courseList = new ArrayList<>();

    @OneToMany(mappedBy = "institute")
    private List<Review> reviewList = new ArrayList<>();

    /**
     *   TODO: 교육기관 평점 표시하는 방법 구현 필요
     *   해당 교육기관 리뷰에 있는 종합 평점의 평균을 사용할 예정
     *   어떻게 구현할지 고민 -> 서비스 단에서 구현
     */

    @Column
    private Double score;

    public Institute(String name) {
        this.name = name;
    }
}
