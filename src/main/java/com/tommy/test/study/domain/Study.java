package com.tommy.test.study.domain;

import com.tommy.test.member.domain.Member;

import java.time.LocalDateTime;

public class Study {

    private final int limit;
    private String name;
    private final StudyStatus status;
    private Member owner;
    private LocalDateTime createdDateTime;

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

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
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
