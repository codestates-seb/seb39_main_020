package com.codestates.BocamDogam.review.entity;

import com.codestates.BocamDogam.audit.BaseTimeEntity;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private int curriculum;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private int fresh;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private int lecturer;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private int care;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private int atmosphere;

    @Column
    @Min(1)
    @Max(5)
    private Double averageScore;

    @Column(nullable = false)
    private String good;

    @Column(nullable = false)
    private String bad;

    @Column
    private String summary;

    @Column
    private int like_count = 0;

    @Column
    private boolean reported;

    @Column
    private boolean block;
}
