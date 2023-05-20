package com.runner.community.runner_community_api.controller;

import com.runner.community.runner_community_api.dto.CreateMemberRequestDTO;
import com.runner.community.runner_community_api.dto.MemberDTO;
import com.runner.community.runner_community_api.dto.MemberSignInRequestDTO;
import com.runner.community.runner_community_api.model.Member;
import com.runner.community.runner_community_api.service.MemberCrewMapService;
import com.runner.community.runner_community_api.service.MemberService;
import com.runner.community.runner_community_api.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestMapping("/api/member/*")
@RestController
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberCrewMapService memberCrewMapService;

    private boolean validateEmail(String email) {
        Pattern emailRegexPattern = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
        Matcher emailMatcher = emailRegexPattern.matcher(email);
        if(!emailMatcher.matches()) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<MemberDTO> memberSignUp(@RequestBody CreateMemberRequestDTO body) throws Exception {

        System.out.println(body.toString());
        if ( body.getEmail() == null || ! validateEmail(body.getEmail()) )
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Invalid Parameter : email");

        if ( body.getMemberName() == null || body.getMemberName().isEmpty() )
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Invalid Parameter : username");

        if ( body.getAddress() == null || body.getAddress().isEmpty() )
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Invalid Parameter : address");

        if ( body.getAge() < 0 || body.getAge() > 150 )
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Invalid Parameter : age (1~150)");

        if ( body.getPassword() == null || body.getPassword().isEmpty() )
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Invalid Parameter : password");

        ZonedDateTime createAt = ZonedDateTime.now();
        ZonedDateTime updateAt = ZonedDateTime.now();

        Member member = new Member(body.getEmail(), body.getPassword(), body.getMemberName(), body.getAge(),
                body.getAddress(), "ROLE_USER", createAt, updateAt);

        Member createdMember = memberService.createMember(member);

        return ResponseEntity.ok(new MemberDTO(createdMember.getMemberId(), createdMember.getEmail(), createdMember.getMemberName(),
                createdMember.getAge(), createdMember.getAddress(), createdMember.getRoleGrant(), createdMember.getCreatedAt(), createdMember.getUpdatedAt()));
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ResponseEntity<MemberDTO> memberSignIn(@RequestBody MemberSignInRequestDTO body) throws RuntimeException {

        if ( body.getEmail() == null || ! validateEmail(body.getEmail()) )
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Invalid Parameter : email");

        if ( body.getPassword() == null || body.getPassword().isEmpty() )
            throw new CustomException(HttpStatus.BAD_REQUEST, "C200000", "Invalid Parameter : password");

        Member member = memberService.signInMember(body.getEmail(), body.getPassword());

        return ResponseEntity.ok(new MemberDTO(member.getMemberId(), member.getEmail(), member.getMemberName(),
                member.getAge(), member.getAddress(), member.getRoleGrant(), member.getCreatedAt(), member.getUpdatedAt()));
    }
}
