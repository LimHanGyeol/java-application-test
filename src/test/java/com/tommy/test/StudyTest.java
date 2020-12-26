package com.tommy.test;

import com.tommy.test.study.domain.Study;
import com.tommy.test.study.domain.StudyStatus;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 클래스의 모든 테스트 메서드에 적용이 된다.
 * 테스트 메서드 네이밍에 대한 전략 구현체를 정의한다.
 * ReplaceUnderscores 는 언더 스코어를 빈 문자로 치환해준다.
 * 테스트 메서드 네이밍은 상황에 따라 달라질 경우가 있으므로 @DisplayName 을 권장한다.
 * 게다가 DisplayNameGeneration 보다 DisplayName 이 우선순위가 더 높다.
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
    @DisplayName("스터디 생성 확인")
    void create() {
        Study study = new Study(5);
        assertThat(study).isNotNull();
        // JUnit message 에서 문자열을 넘기거나 람다식을 쓸 수 있다.
        // 문자열을 넘기면 성공, 실패 상관없이 문자열 연산을 한다.
        // 람다는 필요할 때만 문자열 연산을 하므로 문자열 연산이 많을 경우 람다를 고려하자.
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이" +  StudyStatus.DRAFT + " 여야 합니다.");

        // assertJ
        assertThat(study.getStatus())
                .as("스터디를 처음 만들면 상태값이 DRAFT 여아 합니다.")
                .isEqualTo(StudyStatus.DRAFT);
    }

    @Test
    @DisplayName("스터디 인원 확인")
    void studyMemberCount() {
        Study study = new Study(10);
        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0명보다 커야 한다.");

        // assertJ
        assertThat(study.getLimit() > 0)
                .as("스터디 최대 참석 가능 인원은 0명보다 커야 한다.")
                .isTrue();
    }

    @Test
    @DisplayName("모든 테스트 검증")
    void studyTestAll() {
        Study study = new Study(5);

        assertAll(
                () -> assertNotNull(study),
                // assertJ
                () -> assertThat(study.getStatus()).isEqualTo(StudyStatus.DRAFT),
                () -> assertTrue(study.getLimit() > 0)
        );
    }

    @Test
    @DisplayName("limit 이 음수로 들어올 경우 Exception 발생")
    void exception() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Study(-10);
        });
        assertThat(exception.getMessage()).isEqualTo("limit 은 0보다 커야 한다.");

        // assertJ로 이런 구문도 사용할 수 있다. 개인적으로는 아래와 같은 구문을 선호 한다.
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Study(-10))
                .withMessage("limit 은 0보다 커야 한다.");
    }

    @Test
    @DisplayName("특정 코드가 지정된 시간 내에 완료 해야 하는 경우")
    void timeout() {
        assertTimeout(Duration.ofSeconds(10), () -> new Study(10));

        // 위 단정문은 지정한 시간만큼 테스트를 하는 단점이 있다.
        // 예를들면 10초를 지정하면 해당 테스트 코드는 10초를 구동한 뒤 종료된다.
        // 이를 보완하는 방법으로 예를들어 필요 코드가 1초안에 수행되기만한다면.
        // 즉각적인 Timeout 테스트가 필요한 경우 아래와 같은 구문을 사용할 수 있다.
        // 하지만  assertTimeoutPreemptively 는 조심해서 사용해야 한다.
        // 아래 코드 블럭을 별도의 Thread 에서 실행하기 때문에
        // 만약 ThreadLocal (Spring Transaction) 을 사용하는 코드를 테스트 할 경우,
        // DB 반영 등 예상치 못한 결과가 발생할 수 있다.
        // ThreadLocal 은 전략을 고치지 않는한, 다른 Thread 와 공유되지 않기 때문이다.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    @Test
    @Disabled
    void disable_test_method() {
        System.out.println("Disabled 어노테이션을 사용하면 해당 테스트는 진행하지 않는다.");
        System.out.print("예를 들면 Deprecate 가 됐는데 아직 소스의 정리가 되지 않을 경우..");
        System.out.println("등에 사용 할 수 있을 것 같다.");
    }

}
