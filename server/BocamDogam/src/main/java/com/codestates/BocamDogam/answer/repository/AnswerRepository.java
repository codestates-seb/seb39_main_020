package com.codestates.BocamDogam.answer.repository;

import com.codestates.BocamDogam.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
