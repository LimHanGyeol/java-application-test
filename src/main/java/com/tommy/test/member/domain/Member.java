package com.tommy.test.member.domain;

public class Member {

    private final long id;
    private final String email;

    public Member(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
