package com.codestates.BocamDogam.answer.mapper;

import com.codestates.BocamDogam.answer.dto.AnswerDto;
import com.codestates.BocamDogam.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {

    Answer answerPostToAnswer(AnswerDto.Post requestBody);

    Answer answerPatchToAnswer(AnswerDto.Patch requestBody);

    AnswerDto.Response answerToAnswerResponse(Answer answer);

    List<AnswerDto.Response> AnswerResponseToAnswerResponses(List<Answer> answers);


}
