package com.codestates.BocamDogam.member.service;

import com.codestates.BocamDogam.member.entity.Member;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberService {
    // 회원 등록
    Member createMember(Member member);

    // 단일 회원 정보 조회
    Member findMember(Long memberId);

    // 모든 회원 조회
    Page<Member> findMembers(int page, int size);

    // 회원 정보 수정
    Member updateMember(Member member);

    // 회원 탈퇴
    void deleteMember(Long memberId);
    
    // 이메일로 회원 확인
    Member findMemberByEmail(String email);
    
    // 회원 검증
    Member findVerifiedMember(Long memberId);

    // 등록 회원 검증
    void verifyExistEmail(String Email);

    void verifyWriterMember(String token, Long memberId);
}
