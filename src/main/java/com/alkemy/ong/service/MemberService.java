package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;

public interface MemberService {
    public MemberDto updateMember(MemberDto memberDto,Long id) throws NotFoundException;
}
