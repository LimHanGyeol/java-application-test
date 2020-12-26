package com.tommy.test.member.service;

import com.tommy.test.member.domain.Member;
import com.tommy.test.member.exception.MemberNotFoundException;
import com.tommy.test.study.domain.Study;

public interface MemberService {

    Member findById(Long memberId) throws MemberNotFoundException;

    void validate(Long memberId);

    /**
     * 적절한 예시는 아니지만, 새로운 의존성을 만들기에는 비효율적이라 만들어진 메서드.
     * 새로운 스터디가 만들어질 경우 알림을 준다.
     * @param newStudy 새롭게 만들어진 스터디
     */
    void notify(Study newStudy);
}
