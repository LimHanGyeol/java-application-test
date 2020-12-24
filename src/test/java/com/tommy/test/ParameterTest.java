package com.tommy.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterTest {

//    @EmptySource
//    @NullSource
    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @NullAndEmptySource // composed annotation (커스텀 태그)
    @DisplayName("Null, Empty 테스트")
    void nullAndEmptyTest(String message) {
        System.out.println(message);
    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(ints = {10, 20, 40})
    @DisplayName("스터디 만들기")
    void parameter(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @DisplayName("스터디 생성")
    @CsvSource(value = {"'자바 스터디', 10", "'스프링 스터디', 20"})
    void createStudy(String name, int limit) {
        System.out.println(new Study(name, limit));
    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @DisplayName("스터디 생성 파라미터 타입 변환")
    @CsvSource(value = {"'자바 스터디', 10", "'스프링 스터디', 20"})
    void createStudyType(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getString(0), argumentsAccessor.getInteger(1));
        System.out.println(study);
    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @DisplayName("스터디 생성 파라미터 타입 변환(Aggregator)")
    @CsvSource(value = {"'자바 스터디', 10", "'스프링 스터디', 20"})
    void createStudyToAggregate(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }


    // Aggregator 에는 제약 조건이 있다.
    // static inner class / public class 이어야만 한다.
    static class StudyAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getString(0), argumentsAccessor.getInteger(1));
        }
    }

}
