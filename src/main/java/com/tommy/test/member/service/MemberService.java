package com.tommy.test.member.service;

import com.tommy.test.member.domain.Member;
import com.tommy.test.member.exception.MemberNotFoundException;

import java.util.Optional;

public interface MemberService {

    Member findById(Long memberId) throws MemberNotFoundException;

}
