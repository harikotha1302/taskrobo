package com.practice.taskrobo.exception;
public class TaskAlreadyExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public TaskAlreadyExistException(String message) {
        super(message);
    }
}
