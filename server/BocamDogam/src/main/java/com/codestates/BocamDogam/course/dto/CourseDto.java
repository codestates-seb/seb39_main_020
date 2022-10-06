package com.codestates.BocamDogam.course.dto;

import com.codestates.BocamDogam.course.entity.CourseCategory;
import com.codestates.BocamDogam.institute.entity.Institute;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CourseDto {
    @Getter
    @Setter
    public static class Post {
        private Institute institute;
        private String name;
        private CourseCategory category;
        private LocalDateTime applyStart;
        private LocalDateTime applyEnd;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private boolean supports;
        private boolean remote;
        private int scale;
        private int cost;
    }

    @Getter
    @Setter
    public static class Patch {
        private Long courseId;
        private String name;
        private String description;
        private CourseCategory category;
        private String lecturer;
        private LocalDateTime applyStart;
        private LocalDateTime applyEnd;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private boolean supports;
        private boolean remote;
        private int scale;
        private int cost;
    }

    @Getter
    @Setter
    public static class Response {
        private Long courseId;
        private Institute institute;
        private String name;
        private String description;
        private CourseCategory category;
        private String lecturer;
        private LocalDateTime applyStart;
        private LocalDateTime applyEnd;
        private LocalDateTime StartDate;
        private LocalDateTime EndDate;
        private long period;
        private long scale;
        private long cost;
        private boolean support;
        private boolean remote;
        private String ApplyStatus;
    }
}
