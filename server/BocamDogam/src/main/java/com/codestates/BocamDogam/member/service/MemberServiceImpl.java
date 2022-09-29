package com.codestates.BocamDogam.member.service;

import com.codestates.BocamDogam.auth.utils.CustomAuthorityUtils;
import com.codestates.BocamDogam.exception.BusinessLogicException;
import com.codestates.BocamDogam.exception.ExceptionCode;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.member.repository.MemberRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    // 회원 등록
    public Member createMember(Member member) {
        verifyExistEmail(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    // 단일 회원 정보 조회
    public Member findMember(Long memberId) {
        return findVerifiedMember(memberId);
    }

    // 모든 회원 정보 조회
    @Override
    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    // 회원 정보 수정
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getNickname())
                .ifPresent(nickname -> findMember.setNickname(nickname));

        return memberRepository.save(findMember);
    }

    // 회원 탈퇴
    public void deleteMember(Long memberId) {
        Member member = findVerifiedMember(memberId);
        memberRepository.delete(member);
    }

    // 이메일로 회원 확인
    public Member findMemberByEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        Member member = findMember.get();

        return member;
    }

    // 회원 검증
    public Member findVerifiedMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);

        Member findMember = member.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    // 등록 회원 검증
    public void verifyExistEmail(String email) {
        Optional<Member> verifyMember = memberRepository.findByEmail(email);
        if(verifyMember.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    public void verifyWriterMember(String token, Long memberId) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] splitJwt = token.split("\\.");
        String payload = new String(decoder.decode(splitJwt[1]
                .replace("-", "+")
                .replace ("_", "/")));

        String email = new String(payload.substring(payload.indexOf("email") + 8, payload.indexOf("com")+3));
        System.out.println(email);

        Long requestMemberId = findMemberByEmail(email).getMemberId();

        if (requestMemberId != memberId) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_ALLOWED);
        }


    }
}
