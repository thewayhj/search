package com.api.search.exception;

import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;

public class NoDataException extends NoResultException {
    public static int code;
    public static String message;

    public NoDataException(HttpStatus httpStatus){
        code = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }
}
