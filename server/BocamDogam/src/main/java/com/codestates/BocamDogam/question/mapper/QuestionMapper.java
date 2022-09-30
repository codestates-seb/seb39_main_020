package com.codestates.BocamDogam.question.mapper;

import com.codestates.BocamDogam.question.dto.QuestionDto;
import com.codestates.BocamDogam.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {

    Question questionPostToQuestion(QuestionDto.Post requestBody);

    Question questionPatchToQuestion(QuestionDto.Patch requestBody);

    QuestionDto.Response questionToQuestionResponse(Question question);

    List<QuestionDto.Response> questionResponseToQuestionResponses(List<Question> questions);
}
