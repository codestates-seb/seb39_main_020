package com.codestates.BocamDogam.answer.mapper;

import com.codestates.BocamDogam.answer.dto.AnswerDto;
import com.codestates.BocamDogam.answer.entity.Answer;
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
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostToAnswer(AnswerDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setContent( requestBody.getContent() );

        return answer;
    }

    @Override
    public Answer answerPatchToAnswer(AnswerDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( requestBody.getAnswerId() );
        answer.setContent( requestBody.getContent() );

        return answer;
    }

    @Override
    public AnswerDto.Response answerToAnswerResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response response = new AnswerDto.Response();

        response.setAnswerId( answer.getAnswerId() );
        response.setQuestion( answer.getQuestion() );
        response.setMember( answer.getMember() );
        response.setContent( answer.getContent() );
        response.setLike_count( answer.getLike_count() );
        response.setReported( answer.isReported() );
        response.setBlock( answer.isBlock() );
        response.setCreatedDate( answer.getCreatedDate() );
        response.setModifiedDate( answer.getModifiedDate() );

        return response;
    }

    @Override
    public List<AnswerDto.Response> AnswerResponseToAnswerResponses(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto.Response> list = new ArrayList<AnswerDto.Response>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToAnswerResponse( answer ) );
        }

        return list;
    }
}
