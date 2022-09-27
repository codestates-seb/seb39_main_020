package com.codestates.BocamDogam.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),

    INSTITUTE_NOT_FOUND(404, "Institute not found"),
    INSTITUTE_EXISTS(409, "Institute exists"),

    REVIEW_NOT_FOUND(404, "Review not found"),
    REVIEW_EXISTS(409, "Review exists"),

    COURSE_NOT_FOUND(404, "Course not found"),
    COURSE_EXISTS(409, "Course exists");

    @Getter
    private final int statusCode;

    @Getter
    private final String message;


    ExceptionCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
