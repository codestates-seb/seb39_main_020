package com.codestates.BocamDogam.answer.controller;

import com.codestates.BocamDogam.answer.dto.AnswerDto;
import com.codestates.BocamDogam.answer.entity.Answer;
import com.codestates.BocamDogam.answer.mapper.AnswerMapper;
import com.codestates.BocamDogam.answer.repository.AnswerRepository;
import com.codestates.BocamDogam.answer.service.AnswerService;
import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.service.MemberService;
import com.codestates.BocamDogam.question.entity.Question;
import com.codestates.BocamDogam.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Positive;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/main/qna/questions")
@Validated
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final QuestionService questionService;
    private final MemberService memberService;

    public AnswerController(AnswerService answerService, AnswerRepository answerRepository, AnswerMapper answerMapper, QuestionService questionService, MemberService memberService) {
        this.answerService = answerService;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.questionService = questionService;
        this.memberService = memberService;
    }

    // 답변 등록
    @PostMapping("/{question-id}/answers")
    public ResponseEntity<AnswerDto.Response> postAnswer(@PathVariable("question-id") Long questionId,
                                                         @RequestHeader(value = "Authorization") String token,
                                                         @RequestBody AnswerDto.Post requestBody) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] splitJwt = token.split("\\.");
        String payload = new String(decoder.decode(splitJwt[1]
                .replace("-", "+")
                .replace ("_", "/")));

        String email = new String(payload.substring(payload.indexOf("email") + 8, payload.indexOf("com")+3));

        Member member = memberService.findMemberByEmail(email);
        Question question = questionService.findVerifiedQuestion(questionId);
        Answer answer = answerMapper.answerPostToAnswer(requestBody);
        answer.setMember(member);
        answer.setQuestion(question);

        Answer response = answerService.createAnswer(answer);

        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(response),
                HttpStatus.CREATED);
    }

    // 답변 수정
    @PatchMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity<AnswerDto.Response> patchAnswer(@PathVariable("question-id") Long questionId,
                                                          @PathVariable("answer-id") Long answerId,
                                                          @RequestHeader("Authorization") String token,
                                                          @RequestBody AnswerDto.Patch requestBody) {
        Long writerId = answerService.findAnswer(answerId).getAnswerId();

        memberService.verifyWriterMember(token, writerId);

        requestBody.setAnswerId(answerId);
        Answer patchedAnswer = answerService.updateAnswer(answerMapper.answerPatchToAnswer(requestBody));

        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(patchedAnswer),
                HttpStatus.CREATED);
    }

    // 질문에 등록된 답변 모두 조회
    @GetMapping("/{question-id}/answers")
    public ResponseEntity getAnswers(@PathVariable("question-id") Long questionId,
                                                                           @RequestParam @Positive int page,
                                                                           @RequestParam @Positive int size) {
        Page<Answer> pageAnswers = answerService.findAnswers(page - 1, size);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(answerMapper.AnswerResponseToAnswerResponses(answers),
                        pageAnswers),
                HttpStatus.OK);
    }

    // 답변 삭제
    @DeleteMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("question-id") Long questionId,
                            @PathVariable("answer-id") Long answerId,
                            @RequestHeader("Authorization") String token) {
        memberService.verifyWriterMember(token, answerService.findVerifiedAnswer(answerId).getMember().getMemberId());

        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

