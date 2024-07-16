package com.example.demo.exception;

public class EntityNotFoundException extends TestTaskApplicationException{
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
