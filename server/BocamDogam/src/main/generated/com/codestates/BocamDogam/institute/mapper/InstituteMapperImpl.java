package com.codestates.BocamDogam.institute.mapper;

import com.codestates.BocamDogam.institute.dto.InstituteDto;
import com.codestates.BocamDogam.institute.entity.Institute;
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
public class InstituteMapperImpl implements InstituteMapper {

    @Override
    public Institute institutePostToInstitute(InstituteDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Institute institute = new Institute();

        institute.setName( requestBody.getName() );

        return institute;
    }

    @Override
    public Institute institutePatchToInstitute(InstituteDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Institute institute = new Institute();

        institute.setInstituteId( requestBody.getInstituteId() );
        institute.setName( requestBody.getName() );
        institute.setDescription( requestBody.getDescription() );
        institute.setLocation( requestBody.getLocation() );
        institute.setHomepage( requestBody.getHomepage() );

        return institute;
    }

    @Override
    public InstituteDto.Response instituteToInstituteResponse(Institute institute) {
        if ( institute == null ) {
            return null;
        }

        InstituteDto.Response response = new InstituteDto.Response();

        response.setInstituteId( institute.getInstituteId() );
        response.setName( institute.getName() );
        response.setDescription( institute.getDescription() );
        response.setHomepage( institute.getHomepage() );
        response.setLocation( institute.getLocation() );
        response.setScore( institute.getScore() );

        return response;
    }

    @Override
    public List<InstituteDto.Response> institutesToInstituteResponses(List<Institute> institutes) {
        if ( institutes == null ) {
            return null;
        }

        List<InstituteDto.Response> list = new ArrayList<InstituteDto.Response>( institutes.size() );
        for ( Institute institute : institutes ) {
            list.add( instituteToInstituteResponse( institute ) );
        }

        return list;
    }
}
