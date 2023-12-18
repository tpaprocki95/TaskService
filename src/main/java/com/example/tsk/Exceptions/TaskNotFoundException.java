package com.example.tsk.Exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long id) {
        super("Task with id: "+ id + "not found");
    }
}
