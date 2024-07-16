package com.example.demo.exception;

public class TestTaskApplicationException extends RuntimeException{
    public TestTaskApplicationException(String msg, Throwable t) {
        super(msg, t);
    }

    public TestTaskApplicationException(String msg) {
        super(msg);
    }

    public TestTaskApplicationException(Throwable t) {
        super(t);
    }

    public TestTaskApplicationException() {
    }
}
