package com.practice.miniProject.members.service;

import com.practice.miniProject.auth.util.AuthorityUtil;
import com.practice.miniProject.exception.BusinessLogicException;
import com.practice.miniProject.exception.ExceptionCode;
import com.practice.miniProject.members.entity.Member;
import com.practice.miniProject.members.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityUtil authorityUtil;


    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, AuthorityUtil authorityUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtil = authorityUtil;
    }

    public Member createMember(@RequestBody Member member) {
        existEmail(member.getEmail());
        String encryptPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptPassword);

        List<String> roles = authorityUtil.createRoles(member.getEmail());
        member.setRoles(roles);

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getUsername())
                .ifPresent(username -> findMember.setUsername(username));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(password));


        return memberRepository.save(findMember);
    }

    public List<Member> getMembers() {

        return memberRepository.findAll();
    }

    public Member getMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        return findMember;
    }

    public void deleteMember(long memberId) {

        memberRepository.deleteById(memberId);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member findMember = optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    public void existEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if(optionalMember.isPresent()) {
            new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXIST);
        }
    }
}
