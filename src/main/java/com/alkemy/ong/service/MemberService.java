package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;

public interface MemberService {

    void deleteMember(Long id) throws NotFoundException;

}
