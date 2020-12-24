package com.tommy.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 강의에 나온 properties 등록 보다는 확장 클래스를 작성하는 명시적인 방법을 권장한다.
 * 이유는 의도하지 않은 확장기능을 사용할 수 있기 때문이다.
 */
//@ExtendWith(FindSlowTestExtension.class)
public class GroupTest {

    @RegisterExtension
    static final FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

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

    @Test
    @DisplayName("확장 기능 이용을 위한 스터디 만들기")
    void create() throws InterruptedException {
        Thread.sleep(1005L);
        Study study = new Study(5);
        assertThat(study.getLimit()).isLessThan(10);
    }

    @FastTest
    @DisplayName("커스텀 태그 테스트")
    void customTag() {
        assertThat(new Study(5).getLimit()).isEqualTo(5);
    }

    @RepeatedTest(value = 5, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    @DisplayName("테스트 반복")
    void repeat(RepetitionInfo repetitionInfo) {
        System.out.println("총 반복 횟수 : " + repetitionInfo.getTotalRepetitions());
        System.out.println("현재 반복 횟수 : " + repetitionInfo.getCurrentRepetition());
    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @DisplayName("현재 날씨 확인")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

}
