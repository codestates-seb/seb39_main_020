package com.codestates.BocamDogam.question.service;

import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.post.repository.PostRepository;
import com.codestates.BocamDogam.question.entity.Question;
import com.codestates.BocamDogam.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));

        return questionRepository.save(findQuestion);
    }

    @Override
    public Question findQuestion(Long questionId) {
        return findVerifiedQuestion(questionId);
    }

    @Override
    public Page<Question> findQuestions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.Direction.DESC, "questionId");
        return questionRepository.findAll(pageable);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        questionRepository.delete(findQuestion);
    }

    @Override
    public Question findVerifiedQuestion(Long questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        Question findQuestion = question.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }
}
