package com.tommy.test.study.service;

import com.tommy.test.member.service.MemberService;
import com.tommy.test.study.domain.StudyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    private MemberService memberService;

    @Mock
    private StudyRepository studyRepository;

    @Test
    @DisplayName("스터디 서비스 생성")
    void createStudy() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertThat(studyService).isNotNull();
    }

}
