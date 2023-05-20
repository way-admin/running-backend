package com.runner.community.runner_community_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignInRequestDTO {
    private String email;
    private String password;

    public String toString() {
        return String.format("%s %s", this.email, this.password);
    }
}
