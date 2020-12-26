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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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
        Member member = new Member(1L, "hangyeol@email.com");
        Study study = new Study("Java", 10);

        when(memberService.findById(1L)).thenReturn(member);
        when(studyService.createNewStudy(1L, study)).thenReturn(study);

        assertThat(studyService.createNewStudy(1L, study)).isEqualTo(study);

        // 아래 두 메서드는 Mockito 에서 void 타입 테스트가 되지 않는다.
        // 따로 학습이 더 필요하다. times(), never(), InOrder
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
