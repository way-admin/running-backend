package com.runner.community.runner_community_api.service;

import com.runner.community.runner_community_api.model.Member;
import com.runner.community.runner_community_api.repository.MemberRepository;
import com.runner.community.runner_community_api.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        System.out.println(existMemberEmail(member.getEmail()));
        if(existMemberEmail(member.getEmail()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Email is Exist");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));

        return memberRepository.save(member);
    }

    public Member signInMember(String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomException(HttpStatus.BAD_REQUEST, "C200001", "Member is Not Found"));

        if (encoder.matches(password, member.getPassword())) {
            return member;
        }
        else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200001", "Password Not Matched");
        }
    }

    public boolean existMemberEmail(String email) {
        return (memberRepository.existsByEmail(email)) != 0 ? true : false;
    }

    public Member getMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() ->
                new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Member is Not Found"));
    }

    public Member getMemberByMemberEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() ->
                new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Member is Not Found"));
    }
}
