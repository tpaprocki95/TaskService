package com.example.tsk.Enums;

public enum TaskEnum {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private final String status;

    TaskEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
