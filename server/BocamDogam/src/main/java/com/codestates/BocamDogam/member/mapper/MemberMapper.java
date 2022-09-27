package com.codestates.BocamDogam.member.mapper;

import com.codestates.BocamDogam.member.dto.MemberDto;
import com.codestates.BocamDogam.member.dto.MemberPatchDto;
import com.codestates.BocamDogam.member.dto.MemberPostDto;
import com.codestates.BocamDogam.member.dto.MemberResponseDto;
import com.codestates.BocamDogam.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    // Member memberPostToMember(MemberDto.Post requestBody);
    // Member memberPatchToMember(MemberDto.Patch requestBody);
    // MemberDto.Response memberToMemberResponse(Member member);
    Member memberPostToMember(MemberPostDto requestBody);
    Member memberPatchToMember(MemberPatchDto requestBody);
    MemberResponseDto memberToMemberResponse(Member member);
    List<MemberDto.Response> membersToMemberResponse(List<Member> members);
}
