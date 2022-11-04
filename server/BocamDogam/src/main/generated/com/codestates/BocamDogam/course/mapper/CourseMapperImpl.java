package com.codestates.BocamDogam.course.mapper;

import com.codestates.BocamDogam.course.dto.CourseDto;
import com.codestates.BocamDogam.course.entity.Course;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T15:01:53+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course coursePostToCourse(CourseDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Course course = new Course();

        course.setName( requestBody.getName() );
        course.setCategory( requestBody.getCategory() );
        course.setApplyStart( requestBody.getApplyStart() );
        course.setApplyEnd( requestBody.getApplyEnd() );
        course.setStartDate( requestBody.getStartDate() );
        course.setEndDate( requestBody.getEndDate() );
        course.setScale( requestBody.getScale() );
        course.setCost( requestBody.getCost() );
        course.setRemote( requestBody.isRemote() );
        course.setInstitute( requestBody.getInstitute() );

        return course;
    }

    @Override
    public Course coursePatchToCourse(CourseDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Course course = new Course();

        course.setCourseId( requestBody.getCourseId() );
        course.setName( requestBody.getName() );
        course.setDescription( requestBody.getDescription() );
        course.setCategory( requestBody.getCategory() );
        course.setLecturer( requestBody.getLecturer() );
        course.setApplyStart( requestBody.getApplyStart() );
        course.setApplyEnd( requestBody.getApplyEnd() );
        course.setStartDate( requestBody.getStartDate() );
        course.setEndDate( requestBody.getEndDate() );
        course.setScale( requestBody.getScale() );
        course.setCost( requestBody.getCost() );
        course.setRemote( requestBody.isRemote() );

        return course;
    }

    @Override
    public CourseDto.Response courseToCourseResponse(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto.Response response = new CourseDto.Response();

        response.setCourseId( course.getCourseId() );
        response.setInstitute( course.getInstitute() );
        response.setName( course.getName() );
        response.setDescription( course.getDescription() );
        response.setCategory( course.getCategory() );
        response.setLecturer( course.getLecturer() );
        response.setApplyStart( course.getApplyStart() );
        response.setApplyEnd( course.getApplyEnd() );
        response.setStartDate( course.getStartDate() );
        response.setEndDate( course.getEndDate() );
        response.setPeriod( course.getPeriod() );
        response.setScale( course.getScale() );
        response.setCost( course.getCost() );
        response.setSupport( course.isSupport() );
        response.setRemote( course.isRemote() );
        if ( course.getApplyStatus() != null ) {
            response.setApplyStatus( course.getApplyStatus().name() );
        }

        return response;
    }

    @Override
    public List<CourseDto.Response> coursesToCourseResponse(List<Course> courses) {
        if ( courses == null ) {
            return null;
        }

        List<CourseDto.Response> list = new ArrayList<CourseDto.Response>( courses.size() );
        for ( Course course : courses ) {
            list.add( courseToCourseResponse( course ) );
        }

        return list;
    }
}
