package com.practice.miniProject.auth.memberDetails;

import com.practice.miniProject.auth.util.AuthorityUtil;
import com.practice.miniProject.exception.BusinessLogicException;
import com.practice.miniProject.exception.ExceptionCode;
import com.practice.miniProject.members.entity.Member;
import com.practice.miniProject.members.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final AuthorityUtil authorityUtil;

    public MemberDetailsService(MemberRepository memberRepository, AuthorityUtil authorityUtil) {
        this.memberRepository = memberRepository;
        this.authorityUtil = authorityUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        System.out.println(username);
        System.out.println(optionalMember);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));



        return new UserDetail(findMember);
    }

    private final class UserDetail extends Member implements UserDetails {
        UserDetail (Member member) {
            setMemberId(member.getMemberId());
            setUsername(member.getUsername());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtil.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
