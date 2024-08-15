package com.practice.miniProject.members.mapper;

import com.practice.miniProject.members.dto.MemberDto;
import com.practice.miniProject.members.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "memberId", ignore = true)
    Member memberPostToMember(MemberDto.PostDto requestBody);
    Member memberPutToMember(MemberDto.PutDto requestBody);
    MemberDto.ResponseDto memberToMemberResponse(Member member);
}
