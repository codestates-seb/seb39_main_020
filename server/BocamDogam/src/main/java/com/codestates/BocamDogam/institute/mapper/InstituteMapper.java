package com.codestates.BocamDogam.institute.mapper;

import com.codestates.BocamDogam.institute.dto.InstituteDto;
import com.codestates.BocamDogam.institute.entity.Institute;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InstituteMapper {
    Institute institutePostToInstitute(InstituteDto.Post requestBody);
    Institute institutePatchToInstitute(InstituteDto.Patch requestBody);
    InstituteDto.Response instituteToInstituteResponse(Institute institute);
}
