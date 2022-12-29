package com.api.search.exception;

import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends NoResultException {
    public static int code;
    public static String message;

    public NotFoundException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }
}
