package com.tommy.test.study.service;

import com.tommy.test.member.domain.Member;
import com.tommy.test.member.service.MemberService;
import com.tommy.test.study.domain.Study;
import com.tommy.test.study.domain.StudyRepository;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;

    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Member member = Optional.ofNullable(memberService.findById(memberId))
                .orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id : " + memberId));
        study.setOwner(member);

        Study newStudy = studyRepository.save(study);
        memberService.notify(newStudy);
        return newStudy;
    }
}
