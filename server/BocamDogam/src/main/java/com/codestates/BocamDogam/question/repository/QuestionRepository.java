package com.codestates.BocamDogam.question.repository;

import com.codestates.BocamDogam.question.entity.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
