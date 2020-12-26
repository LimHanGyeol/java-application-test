package com.tommy.test.junit5;

import org.junit.jupiter.api.*;

/**
 * 인스턴스가 class 단위로 유지가 된다.
 * TestInstance 와 TestMethodOrder 어노테이션은 연관관계가 없다.
 * 독립적으로 사용 가능하다.
 * 하지만 유즈케이스 기반으로 시나리오 테스트를 만들때 두 기능을 사용함이 유용하다고 생각한다.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstanceTest {

    private int value = 1;

    @BeforeAll
    void beforeAll() {
        // Lifecycle 이 class 단위면 인스턴스를 1번만 만들어 관리하면 되므로 static 으로 만들 필요가 없다.
        // Lifecycle 이 method 단위면 인스턴스를 공유하여 사용하기 위해 static 으로 사용하는 것 이다.
    }

    @Test
    @Order(2)
    void one() {
        System.out.println(value);
        value++;
    }

    @Test
    @Order(1)
    void two() {
        System.out.println(value);
    }
}
