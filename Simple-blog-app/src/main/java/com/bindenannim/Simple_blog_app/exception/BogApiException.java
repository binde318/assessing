package com.bindenannim.Simple_blog_app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BogApiException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
