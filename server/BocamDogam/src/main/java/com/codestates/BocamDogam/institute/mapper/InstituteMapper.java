package com.codestates.BocamDogam.institute.mapper;

import com.codestates.BocamDogam.institute.dto.InstituteDto;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.post.dto.PostDto;
import com.codestates.BocamDogam.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InstituteMapper {
    Institute institutePostToInstitute(InstituteDto.Post requestBody);
    Institute institutePatchToInstitute(InstituteDto.Patch requestBody);
    InstituteDto.Response instituteToInstituteResponse(Institute institute);
    List<InstituteDto.Response> institutesToInstituteResponses(List<Institute> institutes);
}
