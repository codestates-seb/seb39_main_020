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
    private String name;
    private String category;
    private String applyStart;
    private String applyEnd;
    private String StartDate;
    private String EndDate;
    private int period;
    private int scale;
    private int cost;
    private boolean support;
    private boolean remote;
    private boolean isApplying;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;
}
