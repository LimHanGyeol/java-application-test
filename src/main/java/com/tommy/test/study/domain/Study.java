package com.tommy.test.study.domain;

import com.tommy.test.member.domain.Member;

import java.time.LocalDateTime;

public class Study {

    private static final String INVALID_STUDY_LIMIT = "limit 은 0보다 커야 한다.";

    private final int limit;
    private String name;
    private StudyStatus status;
    private LocalDateTime openedDateTime;

    private Member owner;

    public Study(String name, int limit) {
        validateStudyLimit(limit);
        this.name = name;
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
    }

    public Study(int limit) {
        validateStudyLimit(limit);
        this.status = StudyStatus.DRAFT;
        this.limit = limit;
    }

    public void open() {
        this.status = StudyStatus.OPENED;
        this.openedDateTime = LocalDateTime.now();
    }

    private void validateStudyLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException(INVALID_STUDY_LIMIT);
        }
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

    public LocalDateTime getOpenedDateTime() {
        return openedDateTime;
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
                ", createdDateTime=" + openedDateTime +
                ", owner=" + owner +
                '}';
    }
}
