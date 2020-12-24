package com.tommy.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class OsTest {

    @Test
    @DisplayName("환경 변수가 Local 일 경우 테스트 진행")
    void environmentPath() {
        // assumeTrue 로 조건을 준 후 테스트를 진행할 수 있다.
        String environment = System.getenv("TEST_ENV");
        assumeTrue("LOCAL".equalsIgnoreCase(environment));

        assumingThat("LOCAL".equalsIgnoreCase(environment), () -> {
            Study study = new Study(100);
            assertThat(study.getLimit()).isGreaterThan(0);
        });

        assumingThat("hangyeol".equalsIgnoreCase(environment), () -> {
            Study study = new Study(10);
            assertThat(study.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("환경 변수 테스트의 다른 방법")
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void environmentPath2() {
        assertThat(new Study(10)).isNotNull();
    }

    @Test
    @DisplayName("OS 가 Windows 일 경우 테스트 진행")
    @EnabledOnOs(OS.WINDOWS)
    void osTestEnabled() {
        assertThat(new Study(10)).isNotNull();
    }

    @Test
    @DisplayName("OS 가 Mac 일 경우 테스트 진행하지 않음")
    @DisabledOnOs(OS.MAC)
    void osTestDisabled1() {
        assertThat(new Study(10)).isNotNull();
    }

    @Test
    @DisplayName("OS 가 Mac, Linux 일 경우 테스트 진행하지 않음. 배열로 복수의 value 지정 가능")
    @DisabledOnOs({OS.MAC, OS.LINUX})
    void osTestDisabled2() {
        assertThat(new Study(10)).isNotNull();
    }

    @Test
    @DisplayName("Java Version 종속적인 테스트 가능")
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
    void javaVersion() {
        assertThat(new Study(5)).isNotNull();
    }

    @Test
    @EnabledOnJre({JRE.OTHER})
    void otherJavaVersion() {
        assertThat(new Study(5)).isNotNull();
    }
}
