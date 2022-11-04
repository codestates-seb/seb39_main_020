package com.codestates.BocamDogam.question.service;

import com.codestates.BocamDogam.question.entity.Question;
import org.springframework.data.domain.Page;

public interface QuestionService {


    Question createQuestion(Question question);

    Question updateQuestion(Question question, String email);

    Question findQuestion(Long questionId);

    Page<Question> findQuestions(int page, int size);

    void deleteQuestion(Long questionId, String email);

    Question findVerifiedQuestion(Long questionId);
}
