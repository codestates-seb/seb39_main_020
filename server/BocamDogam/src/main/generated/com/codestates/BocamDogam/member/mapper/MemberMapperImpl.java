package com.codestates.BocamDogam.member.mapper;

import com.codestates.BocamDogam.member.dto.MemberDto;
import com.codestates.BocamDogam.member.dto.MemberPatchDto;
import com.codestates.BocamDogam.member.dto.MemberPostDto;
import com.codestates.BocamDogam.member.dto.MemberResponseDto;
import com.codestates.BocamDogam.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-31T15:01:53+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(MemberPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setNickname( requestBody.getNickname() );
        member.setEmail( requestBody.getEmail() );
        member.setPassword( requestBody.getPassword() );

        return member;
    }

    @Override
    public Member memberPatchToMember(MemberPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( requestBody.getMemberId() );
        member.setNickname( requestBody.getNickname() );

        return member;
    }

    @Override
    public MemberResponseDto memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDto.MemberResponseDtoBuilder memberResponseDto = MemberResponseDto.builder();

        memberResponseDto.memberId( member.getMemberId() );
        memberResponseDto.nickname( member.getNickname() );
        memberResponseDto.email( member.getEmail() );

        return memberResponseDto.build();
    }

    @Override
    public List<MemberDto.Response> membersToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDto.Response> list = new ArrayList<MemberDto.Response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToResponse( member ) );
        }

        return list;
    }

    protected MemberDto.Response memberToResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto.Response response = new MemberDto.Response();

        response.setMemberId( member.getMemberId() );
        response.setNickname( member.getNickname() );
        response.setEmail( member.getEmail() );
        response.setStatus( member.isStatus() );

        return response;
    }
}
