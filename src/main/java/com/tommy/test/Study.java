package com.tommy.test;

public class Study {

    private final int limit;
    private final StudyStatus status;

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit 은 0보다 커야 한다.");
        }
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public StudyStatus getStatus() {
        return status;
    }
}
