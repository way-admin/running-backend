package com.runner.community.runner_community_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MemberDTO {
    private long memberId;
    private String email;

    private String memberName;
    private int age;
    private String address;
    private String roleGrant;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Override
    public String toString() {
        return String.format("%ld %s %s %d %s %s %t %t", this.memberId, this.email,
                this.memberName, this.age, this.address, this.roleGrant, this.createdAt, this.updatedAt);
    }
}
