package com.codestates.BocamDogam.member.controller;

import com.codestates.BocamDogam.member.dto.MemberPostDto;
import com.codestates.BocamDogam.member.mapper.MemberMapper;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 회원 생성 요청
    @PostMapping("/register")
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        Member member = memberMapper.memberPostToMember(memberPostDto);
        Member response = memberService.createMember(member);
        System.out.println(member.getRoles().toString());
        return new ResponseEntity<>(memberMapper.memberToMemberResponse(response), HttpStatus.CREATED);
    }

    // 회원 조회 요청
    // 일단은 필요 없을 수 있음
    @GetMapping("/main/members/{member-id}")
    public ResponseEntity getUser(@PathVariable("member-id") @Positive Long memberId) {
        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(memberMapper.memberToMemberResponse(response), HttpStatus.OK);
    }

    // TODO: 모든 회원 정보 조회 요청


    // TODO: 회원 정보 수정 요청


    // 회원 삭제 요청
    @DeleteMapping("/main/members/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
