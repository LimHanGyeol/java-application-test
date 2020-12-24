package com.tommy.test;

public class Study {

    private final int limit;
    private String name;
    private final StudyStatus status;

    public Study(String name, int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit 은 0보다 커야 한다.");
        }
        this.name = name;
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit 은 0보다 커야 한다.");
        }
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public StudyStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Study{" +
                "limit=" + limit +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
