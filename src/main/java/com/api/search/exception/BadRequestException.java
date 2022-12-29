package com.api.search.exception;

import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends NoResultException {
    public static int code;
    public static String message;

    public BadRequestException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }

}
