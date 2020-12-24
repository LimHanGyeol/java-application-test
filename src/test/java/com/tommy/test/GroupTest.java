package com.tommy.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupTest {

    @Test
    @DisplayName("스터디 만들기 및 태깅 fast")
    @Tag("fast")
    void createFast() {
        Study study = new Study(16);
        assertThat(study.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 및 태깅 slow")
    @Tag("slow")
    void createSlow() {
        Study study = new Study(5);
        assertThat(study.getLimit()).isLessThan(10);
    }
}
