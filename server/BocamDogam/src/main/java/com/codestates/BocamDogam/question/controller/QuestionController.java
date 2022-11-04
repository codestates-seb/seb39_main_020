package com.codestates.BocamDogam.question.controller;

import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.service.MemberService;

import com.codestates.BocamDogam.post.entity.Post;
import com.codestates.BocamDogam.question.dto.QuestionDto;
import com.codestates.BocamDogam.question.entity.Question;
import com.codestates.BocamDogam.question.mapper.QuestionMapper;
import com.codestates.BocamDogam.question.repository.QuestionRepository;
import com.codestates.BocamDogam.question.service.QuestionService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/main/qna")
@Validated
public class QuestionController {

    private final QuestionService questionService;
    private final MemberService memberService;

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionController(QuestionService questionService, MemberService memberService, QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.memberService = memberService;
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }


    // 질문 생성
    @PostMapping("/questions")
    public ResponseEntity postQuestion(@RequestHeader(value = "Authorization") String token,
                                   @RequestBody QuestionDto.Post requestBody) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberService.findMemberByEmail(auth.getPrincipal().toString());

        Question question = questionMapper.questionPostToQuestion(requestBody);
        question.setMember(member);

        Question response = questionService.createQuestion(question);

        return new ResponseEntity<>(questionMapper.questionToQuestionResponse(response),
                HttpStatus.CREATED);
    }

    // 질문 수정
    @PatchMapping("/questions/{question-id}")
    public ResponseEntity patchQuestion(@RequestHeader(value = "Authorization") String token,
                                    @PathVariable("question-id") @Positive Long questionId,
                                    @RequestBody QuestionDto.Patch requestBody) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getPrincipal().toString();

        requestBody.setQuestionId(questionId);
        Question patchedQuestion = questionService.updateQuestion(questionMapper.questionPatchToQuestion(requestBody), email);

        return new ResponseEntity<>(questionMapper.questionToQuestionResponse(patchedQuestion),
                HttpStatus.CREATED);
    }

    // 질문 개별 조회
    @GetMapping("/questions/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive Long questionId) {
        Question question = questionService.findQuestion(questionId);
        return new ResponseEntity<>(questionMapper.questionToQuestionResponse(question), HttpStatus.OK);
    }

    // 질문 전체 조회
    @GetMapping("/questions")
    public ResponseEntity getQuestions(@RequestParam @Positive  int page,
                                        @RequestParam @Positive  int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page-1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionResponseToQuestionResponses(questions),
                        pageQuestions),
                HttpStatus.OK);
    }

    // 질문 삭제
    @DeleteMapping("/questions/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive Long questionId,
                                        @RequestHeader(value = "Authorization") String token) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getPrincipal().toString();
        questionService.deleteQuestion(questionId, email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}