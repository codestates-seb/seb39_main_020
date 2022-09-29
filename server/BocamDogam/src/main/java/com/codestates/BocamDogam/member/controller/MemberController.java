package com.codestates.BocamDogam.member.controller;

import com.codestates.BocamDogam.dto.MultiResponseDto;
import com.codestates.BocamDogam.dto.SingleResponseDto;
import com.codestates.BocamDogam.member.dto.MemberDto;
import com.codestates.BocamDogam.member.dto.MemberPatchDto;
import com.codestates.BocamDogam.member.dto.MemberPostDto;
import com.codestates.BocamDogam.member.mapper.MemberMapper;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/jwtTest")
    public String jwtTest(@RequestHeader("Authorization") String token) {
        return "you've got token. token : " + token;
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 회원 생성 요청
    @PostMapping("/register")
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto requestBody) {
        Member member = memberMapper.memberPostToMember(requestBody);
        Member response = memberService.createMember(member);

        // Member createMember = memberService.createMember(member);
        // MemberDto.Response response = memberMapper.memberToMemberResponse(createMember);

        System.out.println(member.getRoles().toString());
        
        return new ResponseEntity<>(memberMapper.memberToMemberResponse(response), HttpStatus.CREATED);
    }

    // 회원 조회 요청
    // 일단은 필요 없을 수 있음
    @GetMapping("/main/members/{member-id}")
    public ResponseEntity getUser(@PathVariable("member-id") @Positive Long memberId) {
        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(
                memberMapper.memberToMemberResponse(response),
                HttpStatus.OK);
    }

    // 모든 회원 정보 조회 요청
    // 향후 어드민에서 사용될 수 있음
    @GetMapping
    public ResponseEntity getMembers(@RequestParam("page") @Positive int page,
                                     @RequestParam("size") @Positive int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(
                        memberMapper.membersToMemberResponses(members), pageMembers),
                HttpStatus.OK);
    }

    // 회원 정보 수정 요청
    @PatchMapping("/main/members/{member-id}")
    public ResponseEntity updateMember(@PathVariable("member-id") @Positive Long memberId,
                                       @Valid @RequestBody MemberPatchDto requestBody) {
        requestBody.setMemberId(memberId);
        Member member = memberService.updateMember(memberMapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponse(member)),
                HttpStatus.OK);
    }

    // 회원 삭제 요청
    @DeleteMapping("/main/members/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT);
    }
}
