package com.codestates.BocamDogam.answer.service;

import com.codestates.BocamDogam.answer.entity.Answer;
import com.codestates.BocamDogam.answer.repository.AnswerRepository;
import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer updateAnswer(Answer answer) {

        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        return answerRepository.save(findAnswer);
    }

    @Override
    public Answer findAnswer(Long answerId) {
        return findVerifiedAnswer(answerId);
    }

    @Override
    public Page<Answer> findAnswers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.Direction.DESC, "answerId");
        return answerRepository.findAll(pageable);
    }

    @Override
    public void deleteAnswer(Long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        answerRepository.delete(findAnswer);
    }

    @Override
    public Answer findVerifiedAnswer(Long answerId) {
        Optional<Answer> answer = answerRepository.findById(answerId);
        Answer findAnswer = answer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findAnswer;
    }
}
