package com.codestates.BocamDogam.course.dto;

import com.codestates.BocamDogam.course.entity.CourseCategory;
import com.codestates.BocamDogam.institute.entity.Institute;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

public class CourseDto {
    @Getter
    @Setter
    public static class Post {
        private Institute institute;
        private String name;
        private CourseCategory category;
    }

    @Getter
    @Setter
    public static class Patch {

    }

    @Getter
    @Setter
    public static class Response {

    }
}
