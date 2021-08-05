package ru.goodex.gateway.jwt;

import org.apache.http.auth.AuthenticationException;
import org.springframework.http.HttpStatus;


public class JwtAuthenticationException  extends AuthenticationException {

    private HttpStatus httpStatus;


    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public JwtAuthenticationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}