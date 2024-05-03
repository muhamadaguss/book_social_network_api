package com.muhamadagus.booknetwork.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum BusinessErrorCode {

    NO_CODE(0, NOT_IMPLEMENTED,"No Code"),

    INCORRECT_CURRENT_PASSWORD(300,BAD_REQUEST,"Current Password Incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301,BAD_REQUEST,"New Password Does Not Match"),
    ACCOUNT_LOCKED(302,FORBIDDEN,"User Account Locked"),
    ACCOUNT_DISABLED(303,FORBIDDEN,"User Account Disabled"),
    BAD_CREDENTIALS (304,FORBIDDEN,"Login and / or password is incorrect"),

    ;

    @Getter
    private final int code;

    @Getter
    private final String description;

    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCode(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
