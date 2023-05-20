package com.runner.community.runner_community_api.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {
    private final int status;
    private final String errorName;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(HttpStatus error, String code, String message) {
        return ResponseEntity
                .status(error)
                .body(ErrorResponse.builder().status(error.value())
                        .errorName(error.name())
                        .code(code)
                        .message(message)
                        .build()
                );
    }

}
