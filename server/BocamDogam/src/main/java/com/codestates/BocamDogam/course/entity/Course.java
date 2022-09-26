package com.codestates.BocamDogam.course.entity;

import com.codestates.BocamDogam.institute.entity.Institute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String category;

    @Column
    private String applyStart;

    @Column
    private String applyEnd;

    @Column
    private String StartDate;

    @Column
    private String EndDate;

    @Column
    private int period;
    @Column
    private int scale;
    @Column
    private int cost;
    @Column
    private boolean support;
    @Column
    private boolean remote;
    @Column
    private String ApplyStatus;
    // 이넘 타입으로 변경

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;
}
