package com.tommy.test;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 클래스의 모든 테스트 메서드에 적용이 된다.
 * 테스트 메서드 네이밍에 대한 전략 구현체를 정의한다.
 * ReplaceUnderscores 는 언더 스코어를 빈 문자로 치환해준다.
 * 테스트 메서드 네이밍은 상황에 따라 달라질 경우가 있으므로 @DisplayName 을 권장한다.
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll은 Test 메서드가 모두 실행되기 전 딱 1번 호출이 된다.");
        System.out.println("해당 어노테이션은 static 키워드로 사용해야 한다.");
        System.out.println("private 와 returnType은 허용하지 않는다.");
    }

    @BeforeEach
    void setUp() {
        System.out.println("beforeEach 는 모든 Test 코드를 실행하기 이전에 각각 호출이 된다.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll은 Test 메서드가 모두 실행이 된 후 딱 1번 호출이 된다.");
        System.out.println("그 외 내용은 beforeAll 과 같다.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("afterEach 는 모든 Test 코드를 실행한 이후에 각각 호출이 된다.");
    }

    @Test
    @DisplayName("인스턴스 생성 확인")
    void create() {
        Study study = new Study();
        assertThat(study).isNotNull();
    }

    @Test
    @Disabled
    void disable_test_method() {
        System.out.println("Disabled 어노테이션을 사용하면 해당 테스트는 진행하지 않는다.");
        System.out.print("예를 들면 Deprecate 가 됐는데 아직 소스의 정리가 되지 않을 경우..");
        System.out.println("등에 사용 할 수 있을 것 같다.");
    }

}
