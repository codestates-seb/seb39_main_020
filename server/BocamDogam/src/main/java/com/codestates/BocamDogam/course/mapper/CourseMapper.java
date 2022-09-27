package com.codestates.BocamDogam.course.mapper;

import com.codestates.BocamDogam.course.dto.CourseDto;
import com.codestates.BocamDogam.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    Course coursePostToCourse(CourseDto.Post requestBody);
    Course CoursePatchToCourse(CourseDto.Patch requestBody);
    CourseDto.Response courseToCourseResponse(Course course);
    List<CourseDto.Response> coursesToCourseResponse(List<Course> courses);
}
