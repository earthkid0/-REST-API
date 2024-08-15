package com.practice.miniProject.members.controller;

import com.practice.miniProject.members.dto.MemberDto;
import com.practice.miniProject.members.entity.Member;
import com.practice.miniProject.members.mapper.MemberMapper;
import com.practice.miniProject.members.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity PostMember(@Valid @RequestBody MemberDto.PostDto postDto) {
        Member member = memberService.createMember(mapper.memberPostToMember(postDto));

        return new ResponseEntity<>(mapper.memberToMemberResponse(member), HttpStatus.CREATED);
    }

//    @GetMapping("/login")
//    public String loginForm() {
//
//        return "/members/login";
//    }
//
//    @PostMapping("/login")
//    public String login() {
//
//        return "redirect:/posts";
//    }

    @PutMapping("/{member_id}")
    public ResponseEntity PutMember(@PathVariable("member_id") long memberId,
                                    @Valid MemberDto.PutDto putDto) {
        Member member = mapper.memberPutToMember(putDto);
        member.setMemberId(memberId);
        Member updateMember = memberService.updateMember(member);
        MemberDto.ResponseDto response = mapper.memberToMemberResponse(updateMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{member_id}")
    public ResponseEntity getMember(@PathVariable("member_id") long memberId) {
        Member findMember = memberService.getMember(memberId);
        MemberDto.ResponseDto response = mapper.memberToMemberResponse(findMember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> members = memberService.getMembers();

        List<MemberDto.ResponseDto> response = members.stream()
                .map(member -> mapper.memberToMemberResponse(member))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member_id}")
    public ResponseEntity DeleteMember(@PathVariable("member_id") long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
