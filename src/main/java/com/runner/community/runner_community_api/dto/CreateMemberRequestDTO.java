package com.runner.community.runner_community_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateMemberRequestDTO {
    private String email;
    private String password;
    private String memberName;
    private int    age;
    private String address;

    @Override
    public String toString() {
        return String.format("%s %s %s %d %s", this.email, this.password, this.memberName, this.age, this.address);
    }
}
