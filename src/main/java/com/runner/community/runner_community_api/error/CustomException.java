package com.runner.community.runner_community_api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus errorStatus;
    private final String errorCode;
    private final String errorMessage;
}
