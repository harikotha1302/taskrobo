package com.practice.taskrobo.exception;
public class CategoryAlreadyExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
