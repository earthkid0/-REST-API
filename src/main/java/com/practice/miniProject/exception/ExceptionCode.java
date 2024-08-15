package com.practice.miniProject.exception;

import lombok.Getter;

public enum ExceptionCode {
    POST_NOT_FOUND(404, "Post Not Found"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_EMAIL_EXIST(409, "Email Exist"),
    PRODUCT_NOT_FOUND(404, "Product Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
