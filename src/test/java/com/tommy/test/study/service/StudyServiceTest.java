package com.tommy.test.study.service;

import com.tommy.test.member.domain.Member;
import com.tommy.test.member.exception.MemberNotFoundException;
import com.tommy.test.member.service.MemberService;
import com.tommy.test.study.domain.Study;
import com.tommy.test.study.domain.StudyRepository;
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

    @Test
    @DisplayName("스터디 서비스 생성")
    void createStudy() {
        studyService = new StudyService(memberService, studyRepository);
        assertThat(studyService).isNotNull();
    }

    @Test
    @DisplayName("새로운 스터디 생성")
    void createNewStudy() {
        Member member = new Member(1L, "hangyeol@email.com");

        when(memberService.findById(1L)).thenReturn(member);

        Study study = new Study("Java", 10);
        studyService.createNewStudy(1L, study);
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
