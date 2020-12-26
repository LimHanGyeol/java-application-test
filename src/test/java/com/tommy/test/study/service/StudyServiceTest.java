package com.tommy.test.study.service;

import com.tommy.test.member.domain.Member;
import com.tommy.test.member.exception.MemberNotFoundException;
import com.tommy.test.member.service.MemberService;
import com.tommy.test.study.domain.Study;
import com.tommy.test.study.domain.StudyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    private MemberService memberService;

    @Mock
    private StudyRepository studyRepository;

    private StudyService studyService;

    @BeforeEach
    void setUp() {
        studyService = new StudyService(memberService, studyRepository);
    }

    @Test
    @DisplayName("새로운 스터디 생성")
    void createNewStudy() {
        // Given 주어진 상황
        Member member = new Member(1L, "hangyeol@email.com");
        Study study = new Study("Java", 10);

        // when 은 given 의 영역에 해당하는데, 메서드는 적합하지 않다.
        // Mockito 는 BDD 스타일을 지원한다.
        // when(memberService.findById(1L)).thenReturn(member);
        // when(studyService.createNewStudy(1L, study)).thenReturn(study);

        given(memberService.findById(1L)).willReturn(member);
        given(studyRepository.save(study)).willReturn(study);

        // When 무엇인가를 할 때
        Study newStudy = studyService.createNewStudy(1L, study);

        // Then 이럴 것 이다.
        assertThat(newStudy).isEqualTo(study);
        then(memberService).should(times(1)).notify(study);
        then(memberService).shouldHaveNoMoreInteractions();

        // 아래 두 메서드는 Mockito 에서 void 타입 테스트가 되지 않는다.
        // 따로 학습이 더 필요하다. times(), never(), InOrder
        // 하지만 then 에서는 문제없이 작동한다.
        // verify(memberService, times(1)).notify(study); 메서드가 호출된 횟수 확인
        // verify(memberService, never()).validate(any()); 메서드가 호출되지 않음 확인
    }

    @Test
    @DisplayName("validate 할 경우 Exception 발생")
    void throwException() {
        doThrow(new MemberNotFoundException())
                .when(memberService)
                .validate(1L);

        assertThatExceptionOfType(MemberNotFoundException.class)
                .isThrownBy(() -> memberService.validate(1L));
    }

    @Test
    @DisplayName("여러번의 호출 확인")
    void createRequest() {
        Member member = new Member(1L, "hangyeol@email.com");

        when(memberService.findById(1L))
                .thenReturn(member)
                .thenThrow(new MemberNotFoundException());

        assertThat(memberService.findById(1L)).isEqualTo(member);

        assertThatExceptionOfType(MemberNotFoundException.class)
                .isThrownBy(() -> {
                    memberService.findById(1L);
                });
    }

}
