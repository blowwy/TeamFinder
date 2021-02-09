package com.artsmuzi.teamfinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamNotFoundedException extends RuntimeException {
    public TeamNotFoundedException() {
        super();
    }

    public TeamNotFoundedException(String message) {
        super(message);
    }

    public TeamNotFoundedException(String message, Throwable clause) {
        super(message, clause);
    }
}
