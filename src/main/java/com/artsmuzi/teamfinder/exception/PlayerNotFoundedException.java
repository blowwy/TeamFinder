package com.artsmuzi.teamfinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundedException extends RuntimeException {
    public PlayerNotFoundedException() {
        super();
    }

    public PlayerNotFoundedException(String message) {
        super(message);
    }

    public PlayerNotFoundedException(String message, Throwable cause) {
        super(message, cause);
    }
}
