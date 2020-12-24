package com.tommy.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * 인스턴스가 class 단위로 유지가 된다.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InstanceTest {

    private int value = 1;

    @BeforeAll
    void beforeAll() {
        // Lifecycle 이 class 단위면 인스턴스를 1번만 만들어 관리하면 되므로 static 으로 만들 필요가 없다.
        // Lifecycle 이 method 단위면 인스턴스를 공유하여 사용하기 위해 static 으로 사용하는 것 이다.
    }

    @Test
    void one() {
        System.out.println(value);
        value++;
    }

    @Test
    void two() {
        System.out.println(value);
    }
}
