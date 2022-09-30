package com.codestates.BocamDogam.answer.service;

import com.codestates.BocamDogam.answer.entity.Answer;
import org.springframework.data.domain.Page;

public interface AnswerService {


    Answer createAnswer(Answer answer);

    Answer updateAnswer(Answer answer);

    Answer findAnswer(Long answerId);

    Page<Answer> findAnswers(int page, int size);

    void deleteAnswer(Long answerId);

    Answer findVerifiedAnswer(Long answerId);

}
