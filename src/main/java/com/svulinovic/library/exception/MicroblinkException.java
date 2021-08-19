package com.svulinovic.library.exception;

public class MicroblinkException extends RuntimeException {

    public MicroblinkException(String message) {
        super(message);
    }

    public MicroblinkException(String message, Throwable e) {
        super(message, e);
    }
}
